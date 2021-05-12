package ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import jsonFileHandling.JsonReader;
import jsonFileHandling.JsonWriter;
import jsonFileHandling.MatchResultData;
import matrixRepresentation.BoardMatrix;
import org.tinylog.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Class for handling a Game Over situation.
 */
public class GameOverHandler {

    /**
     * Object containing the names of players.
     */
    private NameInputHandler nameInputs;

    /**
     * Object containing the file used for saving results.
     */
    private SaveFileInputHandler fileInput;

    /**
     * Object representing the board with a 2-dimensional array.
     */
    private BoardMatrix matrix;

    /**
     * Stores the turn where game over handling was initiated.
     */
    char losingTurn;
    /**
     * Stores the color of the player who lost the match.
     */
    char loserColor;
    /**
     * Stores the name of the player who lost the match.
     */
    String loserName;
    /**
     * Stores the score of the player who lost the match.
     */
    int loserScore;

    /**
     * Stores the current date and time.
     */
    public String dateTime = LocalDate.now() + " " + LocalTime.now();
    /**
     * Stores the name of the red player.
     */
    public String redName;
    /**
     * Stores the name of the blue player.
     */
    public String blueName;
    /**
     * Stores the score of the red player.
     */
    public int redScore;
    /**
     * Stores the score of the blue player.
     */
    public int blueScore;
    /**
     * Stores the color of the winner.
     */
    public char winnerColor;
    /**
     * Stores the name of the winner.
     */
    public String winnerName;
    /**
     * Stores the score of the winner.
     */
    public int winnerScore;

    /**
     * Creates a {@link GameOverHandler} object.
     */
    public GameOverHandler() {
    }

    /**
     * Creates a {@link GameOverHandler} object.
     *
     * @param losingTurn          The turn where game over handling was initiated.
     * @param boardGameController The main controller of the application.
     */
    public GameOverHandler(char losingTurn, BoardGameController boardGameController) {
        this.losingTurn = losingTurn;
        this.fileInput = boardGameController.saveFileInputHandler;
        this.nameInputs = boardGameController.nameInputHandler;
        redName = nameInputs.redName;
        blueName = nameInputs.blueName;
        this.matrix = boardGameController.matrix;
        calculateScores();
        logResults();
        saveResults();
        gameOverWindow(boardGameController);
    }

    /**
     * Calculates scores from matrix.
     */
    public void calculateScores() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                if (matrix.get(row, column) == 'r') {
                    ++redScore;
                } else if (matrix.get(row, column) == 'b') {
                    ++blueScore;
                } else if (matrix.get(row, column) == 'e') {
                    if (losingTurn == 'r') {
                        ++blueScore;
                    } else if (losingTurn == 'b') {
                        ++redScore;
                    }
                }
            }
        }
        if (redScore > blueScore) {
            lost('r');
        } else if (redScore < blueScore) {
            lost('b');
        }
//Last player to move will win
        else if (redScore == blueScore) {
            lost(losingTurn);
        }
    }

    /**
     * Gives value to stored attributes.
     *
     * @param loserColor The color of the player who lost.
     */
    public void lost(char loserColor) {
        if (loserColor == 'r') {
            winnerColor = 'r';
            loserColor = 'b';
            winnerName = nameInputs.redName;
            loserName = nameInputs.blueName;
            winnerScore = redScore;
            Logger.debug("first" + winnerScore);
            loserScore = blueScore;
        } else if (loserColor == 'b') {

        }
    }

    /**
     * Logs the result of the match to console.
     */
    public void logResults() {
        Logger.trace("Winner is " + winnerName + " with " + winnerScore + "points.");
        Logger.trace("Loser is " + loserName + " with " + loserScore + "points.");
    }

    /**
     * Saves results to given json file.
     */
    public void saveResults() {
        JsonReader reader = new JsonReader(fileInput.saveFile);
        ArrayList<MatchResultData> results = reader.getAll();

        results.add(new MatchResultData(dateTime, redName, blueName, redScore, blueScore, winnerColor, winnerName, winnerScore));

        JsonWriter writer = new JsonWriter();
        writer.writeToFile(fileInput.saveFile, results);
    }

    /**
     * Creates a window that appears after a game over.
     *
     * @param boardGameController The main controller of the application.
     */
    public void gameOverWindow(BoardGameController boardGameController) {
        Stage stage = new Stage();
        stage.setTitle("Game Over!");
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/gameOverWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        Button newGame = (Button) scene.lookup("#newGameButton");
        Button exit = (Button) scene.lookup("#exitButton");

        newGame.setOnAction(t -> {
                    matrix.toStartPosition();
                    boardGameController.stopGameOverGeneration = false;
                    stage.close();
                }
        );

        exit.setOnAction(t -> {
                    Platform.exit();
                }
        );

        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

    }

}
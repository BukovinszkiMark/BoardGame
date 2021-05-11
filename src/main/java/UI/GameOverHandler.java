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

public class GameOverHandler {
    private NameInputHandler nameInputs;
    private SaveFileInputHandler fileInput;
    private BoardMatrix matrix;

    char losingTurn;

    char loserColor; //
    String loserName; //
    int loserScore; //

    //The ones save in savefile
    public String dateTime = LocalDate.now() + " " + LocalTime.now();
    public String redName;
    public String blueName;
    public int redScore;
    public int blueScore;
    public char winnerColor; //
    public String winnerName; //
    public int winnerScore = 100; //

    public GameOverHandler() { }

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

    public void calculateScores() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                if(matrix.get(row, column) == 'r') {
                    ++redScore;
                }
                else if(matrix.get(row, column) == 'b') {
                    ++blueScore;
                }
                else if(matrix.get(row, column) == 'e') {
                    if (losingTurn == 'r') {
                        ++blueScore;
                    }
                    else if (losingTurn == 'b') {
                        ++redScore;
                    }
                }
            }
        }
        if (redScore>blueScore){
            lost('r');
        }
        else if (redScore<blueScore){
            lost('b');
        }
        //Last player to move will win
        else if (redScore == blueScore){
            lost(losingTurn);
        }
    }

    public void lost(char loserColor) {
        if (loserColor == 'r') {
            winnerColor = 'r';
            loserColor = 'b';
            winnerName = nameInputs.redName;
            loserName = nameInputs.blueName;
            winnerScore = redScore;
            Logger.debug("first" + winnerScore);
            loserScore = blueScore;
        }
        else if (loserColor == 'b') {

        }
    }

    public void logResults() {
        Logger.trace("Winner is " + winnerName + " with " + winnerScore + "points.");
        Logger.trace("Loser is " + loserName + " with " + loserScore + "points.");
    }

    public void saveResults() {
        JsonReader reader = new JsonReader(fileInput.saveFile);
        ArrayList<MatchResultData> results = reader.getAll();

        results.add(new MatchResultData(dateTime, redName, blueName, redScore, blueScore, winnerColor, winnerName, winnerScore));

        JsonWriter writer = new JsonWriter();
        writer.writeToFile(fileInput.saveFile, results);
    }

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
            boardGameController.stopGameOverGEneration = false;
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

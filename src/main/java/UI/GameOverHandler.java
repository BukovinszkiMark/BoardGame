package UI;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import matrixRepresentation.BoardMatrix;
import org.tinylog.Logger;

import java.io.IOException;

public class GameOverHandler {
    private NameInputHandler inputs;
    private BoardMatrix matrix;

    char loser;
    char winner;
    int redScore;
    int blueScore;
    String loserName;
    String winnerName;
    int winnerScore;
    int loserScore;

    public GameOverHandler(char loser, NameInputHandler nameInputHandler, BoardMatrix boardMatrix) {
        this.loser = loser;
        if (loser == 'r') {this.winner = 'b';}
        else if (loser == 'b') {this.winner = 'r';}
        this.inputs = nameInputHandler;
        this.matrix = boardMatrix;
        calculateScores();
        logResults();

        //gameOverWindow();
        //Currently not working for unknown reason
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
                    if (winner == 'r') {
                        ++redScore;
                    }
                    else if (winner == 'b') {
                        ++blueScore;
                    }
                }
            }
        }
        if (winner == 'r') {
            winnerName = inputs.redName;
            loserName = inputs.blueName;
            winnerScore = redScore;
            loserScore = blueScore;
        }
        else if (winner == 'b') {
            winnerName = inputs.blueName;
            loserName = inputs.redName;
            winnerScore = blueScore;
            loserScore = redScore;
        }
    }

    public void logResults() {
        Logger.trace("Winner is " + winnerName + " with " + winnerScore + "points.");
        Logger.trace("Loser is " + loserName + " with " + loserScore + "points.");
    }

    public void gameOverWindow() {
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

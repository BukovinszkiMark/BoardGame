package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * Class for handling names gives by players as input.
 */
public class NameInputController {
    /**
     * Name of the red player.
     */
    public String redName;
    /**
     * Name of the blue player.
     */
    public String blueName;

    /**
     * The field containing the red players input.
     */
    private TextField redInput;
    /**
     * The field containing the blue players input.
     */
    private TextField blueInput;
    /**
     * The button that starts the match.
     */
    private Button start;

    /**
     * Creates a {@link NameInputController} object.
     */
    public NameInputController() {
    }

    /**
     * Creates window and handles input.
     */
    public void askNames() {

        Stage stage = new Stage();
        stage.setTitle("Board Game");
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/nameInput.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        redInput = (TextField) scene.lookup("#redInput");
        blueInput = (TextField) scene.lookup("#blueInput");
        start = (Button) scene.lookup("#start");

        start.setOnAction(t -> stage.close());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        redName = redInput.getText();
        blueName = blueInput.getText();

        Logger.trace("Red player name: " + redName);
        Logger.trace("Blue player name: " + blueName);

    }
}

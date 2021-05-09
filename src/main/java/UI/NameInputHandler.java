package UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class NameInputHandler {
    String redName;
    String blueName;

    private TextField redInput;
    private TextField blueInput;
    private Button start;

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

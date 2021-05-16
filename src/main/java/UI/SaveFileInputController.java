package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class for handling save file input from user.
 */
public class SaveFileInputController {
    /**
     * The file chosen by user.
     */
    public File saveFile;

    /**
     * Creates a {@link SaveFileInputController} object.
     */
    public SaveFileInputController() {
    }

    /**
     * Creates windows and handles input.
     */
    public void askFile() {
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Button button = new Button("Choose save file (JSON)");
        button.setOnAction(t -> {
            FileChooser chooser = new FileChooser();
            saveFile = chooser.showOpenDialog(stage);
            stage.close();
        });
        root.getChildren().add(button);
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.setTitle("Choose save file");
        stage.showAndWait();
    }
}


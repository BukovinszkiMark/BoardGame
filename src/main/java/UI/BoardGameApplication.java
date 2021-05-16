package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * Class for Launching the application.
 */
public class BoardGameApplication extends Application {
    /**
     * Starts the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Logger.debug(stage);
        Parent root = FXMLLoader.load(getClass().getResource("/board.fxml"));
        stage.setTitle("Board Game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setHeight(700);
        stage.setWidth(700);
        stage.show();
    }

}

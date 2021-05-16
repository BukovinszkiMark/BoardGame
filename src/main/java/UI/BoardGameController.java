package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.tinylog.Logger;
import matrixRepresentation.BoardMatrix;


/**
 * Class acting as the main controller of the application.
 */
public class BoardGameController {

    private MainController mainController;

    @FXML
    private GridPane board;

    /**
     * Does every preparation necessary for starting a match.
     */
    @FXML
    private void initialize() {
        mainController = new MainController();
        mainController.askFile();
        mainController.askNames();

        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }

        updateBoardFromMatrix(mainController.matrix);
    }

    /**
     * Detects and handles mouse clicks.
     *
     * @param event The event detected.
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        StackPane square = (StackPane) event.getSource();
        int row = GridPane.getRowIndex(square);
        int column = GridPane.getColumnIndex(square);
        Logger.trace("Click at: (%d,%d)".formatted(row, column));
        mainController.handleMouseClick(row, column);
        updateBoardFromMatrix(mainController.matrix);
        mainController.gameOverCheck();
    }

    /**
     * Creates and returns StackPanes necessary for creating the board.
     *
     * @return an object representing one square on the board
     */
    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(30);
        piece.setFill(Color.TRANSPARENT);
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    /**
     * @param matrix The matrix where we store the changes
     */
    private void updateBoardFromMatrix(BoardMatrix matrix) {
        for (Node child : board.getChildren()) {
            int r = GridPane.getRowIndex(child);
            int c = GridPane.getColumnIndex(child);
            StackPane square = (StackPane) child;
            Circle circle = (Circle) square.getChildren().get(0);
            switch (matrix.get(r, c)) {
                case 'e': {
                    circle.setFill(Color.TRANSPARENT);
                    break;
                }
                case 'r': {
                    circle.setFill(Color.RED);
                    break;
                }
                case 'b': {
                    circle.setFill(Color.BLUE);
                    break;
                }
                case 's': {
                    circle.setFill(Color.YELLOWGREEN);
                    break;
                }
                case 'w': {
                    square.getStyleClass().set(0, "wall");
                    break;
                }
            }

        }
    }

}

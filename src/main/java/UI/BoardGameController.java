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
import matrixRepresentation.MouseClickHandler;

public class BoardGameController {
    public BoardMatrix matrix;
    private MouseClickHandler mouseClickHandler;
    public NameInputHandler nameInputHandler;

    public boolean stopGameOverGEneration = false;

    @FXML
    private GridPane board;

    @FXML
    private void initialize() {
        nameInputHandler = new NameInputHandler();
        nameInputHandler.askNames();

        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }

        matrix = new BoardMatrix();
        mouseClickHandler = new MouseClickHandler(this.matrix);
        updateBoardFromMatrix(matrix);
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        StackPane square = (StackPane) event.getSource();
        int row = GridPane.getRowIndex(square);
        int column = GridPane.getColumnIndex(square);
        Logger.trace("Click at: (%d,%d)".formatted(row, column));
        mouseClickHandler.handle(row, column);
        updateBoardFromMatrix(matrix);
        gameOverCheck();
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(30);
        piece.setFill(Color.TRANSPARENT);
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private void updateBoardFromMatrix(BoardMatrix matrix) {
        for (Node child : board.getChildren()) {
            int r = GridPane.getRowIndex(child);
            int c = GridPane.getColumnIndex(child);
            StackPane square = (StackPane) child;
            Circle circle = (Circle) square.getChildren().get(0);
            switch (matrix.get(r, c)) {
                case 'e':{circle.setFill(Color.TRANSPARENT);break;}
                case 'r':{circle.setFill(Color.RED);break;}
                case 'b':{circle.setFill(Color.BLUE);break;}
                case 's':{circle.setFill(Color.YELLOWGREEN);break;}
                case 'w':{square.getStyleClass().set(0, "wall");break;}
            }

        }
    }

    public void gameOverCheck() {
        if(matrix.gameOverCheck(mouseClickHandler.turnHandler.player()) && !stopGameOverGEneration) {
            stopGameOverGEneration = true;
            GameOverHandler gameOverHandler = new GameOverHandler(mouseClickHandler.turnHandler.player(), this);
        }
    }
}

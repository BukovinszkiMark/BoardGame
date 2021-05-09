import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardGameController {

    @FXML
    private GridPane board;

    private BoardMatrix matrix;

    @FXML
    private void initialize() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }
        this.matrix = new BoardMatrix(board.getRowCount(), board.getColumnCount());
        updateBoardFromMatrix(matrix);
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(30);
        piece.setFill(Color.TRANSPARENT);
        square.getChildren().add(piece);
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
                case 'w':{square.getStyleClass().set(0, "wall");break;}
            }
        }
    }

}

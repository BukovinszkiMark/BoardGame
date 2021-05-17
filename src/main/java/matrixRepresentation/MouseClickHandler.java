package matrixRepresentation;

import org.tinylog.Logger;
import ui.MainController;

/**
 * Class used for handling mouse clicks.
 */
public class MouseClickHandler {

    /**
     * Matrix representing the board.
     */
    private BoardMatrix matrix;
    /**
     * Object for handling turns.
     */
    public TurnHandler turnHandler;

    /**
     * Stores whether a piece has been selected.
     */
    boolean selectionOn = false;
    /**
     * Color of the selected piece.
     */
    char selectionColor;
    /**
     * First index of the selected piece.
     */
    int selectionRow;
    /**
     * Second index of the selected piece.
     */
    int selectionColumn;

    /**
     * Creates a {@link MouseClickHandler} object.
     *
     * @param mainController The main controller of the application.
     */
    public MouseClickHandler(MainController mainController) {
        this.matrix = mainController.matrix;
        this.turnHandler = mainController.turnHandler;
    }

    /**
     * Handles mouse clicks.
     *
     * @param row    First index of position of click.
     * @param column Second index of position of click.
     */
    public void handle(int row, int column) {
        if (!selectionOn) {
            switch (matrix.get(row, column)) {
                case 'e': {
                    place(row, column);
                    break;
                }
                case 'r': {
                    select(row, column);
                    break;
                }
                case 'b': {
                    select(row, column);
                    break;
                }
            }
        } else {
            if (row == selectionRow && column == selectionColumn) {
                matrix.set(row, column, turnHandler.player());
                selectionOn = false;
            }
            if (matrix.get(row, column) == 'e') {
                moveTo(row, column);
            }
        }
    }

    /**
     * Handles an attempt to place.
     *
     * @param row    First index of position of click.
     * @param column Second index of position of click.
     */
    public void place(int row, int column) {
        boolean canPlace = false;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (matrix.exists(i, j)) {
                    if (matrix.get(i, j) == turnHandler.player()) {
                        canPlace = true;
                    }
                }
            }
        }
        if (canPlace) {
            matrix.set(row, column, turnHandler.player());
            Logger.trace("New piece placed at: (%d,%d)".formatted(row, column));
            changeSurroundings(row, column);
            turnHandler.next();
        }
    }

    /**
     * Handles selection of a piece.
     *
     * @param row    First index of position of click.
     * @param column Second index of position of click.
     */
    public void select(int row, int column) {
        if (matrix.get(row, column) == turnHandler.player()) {
            selectionColor = turnHandler.player();
            selectionRow = row;
            selectionColumn = column;
            selectionOn = true;
            Logger.trace("Selected: (%d,%d)".formatted(row, column));
            matrix.set(row, column, 's');
        }
    }

    /**
     * Handles an attempt to move with a selected piece.
     *
     * @param row    First index of position of click.
     * @param column Second index of position of click.
     */
    public void moveTo(int row, int column) {
        int rowDiff = Math.abs(row - selectionRow);
        int columnDiff = Math.abs(column - selectionColumn);
        if ((rowDiff == 0 && columnDiff == 2) || (rowDiff == 2 && columnDiff == 0)) {
            matrix.set(selectionRow, selectionColumn, 'e');
            matrix.set(row, column, turnHandler.player());
            changeSurroundings(row, column);
            selectionOn = false;
            Logger.trace("Moved to: (%d,%d)".formatted(row, column));
            turnHandler.next();
        }
    }

    /**
     * Handles changes on the board after a move.
     *
     * @param row    First index of position of click.
     * @param column Second index of position of click.
     */
    public void changeSurroundings(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (matrix.exists(i, j)) {
                    if (matrix.get(i, j) == turnHandler.enemy()) {
                        matrix.set(i, j, turnHandler.player());
                    }
                }
            }
        }
    }
}
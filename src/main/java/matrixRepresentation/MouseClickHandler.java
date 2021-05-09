package matrixRepresentation;

import javafx.scene.paint.Color;

public class MouseClickHandler {
    /*
    possible actions:
    -click on square to put piece
    -click on piece to move it
    -invalid action
     */


    BoardMatrix matrix;
    TurnHandler turnHandler;

    boolean selectionOn = false;
    char selectionColor;
    int selectionRow;
    int selectionColumn;

    public MouseClickHandler(BoardMatrix matrix) {
        this.matrix = matrix;
        this.turnHandler = new TurnHandler();
    }

    public void handle (int row, int column){
        if (!selectionOn) {
            switch (matrix.get(row, column)) {
                case 'e': { place(row, column); break; }
                case 'r': { select(row, column);break; }
                case 'b': { select(row, column);break; }
            }
        }
        else {
            if (row == selectionRow && column == selectionColumn) {
                matrix.set(row, column, turnHandler.player());
                selectionOn = false;
            }
            if (matrix.get(row, column) == 'e') {
                moveTo(row, column);
            }
        }
    }

    public void place(int row, int column) {
        boolean isValid = false;
        for (int i = row-1; i<=row+1; i++) {
            for (int j = column-1; j<=column+1; j++) {
                if( 0<=i && i<=5 && 0<=j && j<=5 ) {
                    if (matrix.get(i, j) == turnHandler.player()) {
                        isValid = true;
                    }
                }
            }
        }
        if(isValid){
            matrix.set(row, column, turnHandler.player());
            changeSurroundings(row, column);
            turnHandler.next();
        }
    }

    public void select(int row, int column) {
        if (matrix.get(row, column) == turnHandler.player()) {
            selectionColor = turnHandler.player();
            selectionRow = row;
            selectionColumn = column;
            selectionOn = true;
            matrix.set(row, column, 's');
        }
    }

    public void moveTo(int row, int column) {
        int rowDiff = Math.abs(row - selectionRow);
        int columnDiff = Math.abs(column - selectionColumn);
        if ((rowDiff == 0 && columnDiff == 2) || (rowDiff == 2 && columnDiff == 0)) {
            matrix.set(selectionRow, selectionColumn, 'e');
            matrix.set(row, column, turnHandler.player());
            changeSurroundings(row, column);
            selectionOn = false;
            turnHandler.next();
        }
    }

    public void changeSurroundings(int row, int column) {
        for (int i = row-1; i<=row+1; i++) {
            for (int j = column-1; j<=column+1; j++) {
                if( 0<=i && i<=5 && 0<=j && j<=5 ) {
                    if (matrix.get(i, j) == turnHandler.enemy()) {
                        matrix.set(i, j, turnHandler.player());
                    }
                }
            }
        }
    }
}

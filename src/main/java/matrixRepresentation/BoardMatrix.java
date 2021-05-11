package matrixRepresentation;

import org.tinylog.Logger;

public class BoardMatrix {
    /*
    matrix[row][column]

    values:
    'e' = empty
    'r' = red
    'b' = blue
    'w' = wall
    's' = selected
     */

    private char[][] matrix;

    public BoardMatrix() {
        this.matrix = new char[6][6];
        this.toStartPosition();
    }

    public void toStartPosition() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                matrix[row][column] = 'e';
            }
        }
        matrix[0][0] = 'r';
        matrix[5][5] = 'r';
        matrix[0][5] = 'b';
        matrix[5][0] = 'b';
        matrix[3][3] = 'w';
    }

    public char get(int row, int column) {
        return matrix[row][column];
    }

    public void set(int row, int column, char c) {
        matrix[row][column] = c;
    }

    public boolean gameOverCheck(char player){
        boolean bool = true;
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                if ((matrix[row][column] == player) || (matrix[row][column] == 's')) {
                    if (movePossible(row, column)) {
                        bool = false;
                    }
                }
            }
        }
        return true;
    }

    public boolean movePossible(int row, int column){
        return canPlace(row, column) || canMove(row, column);
    }

    public boolean canPlace(int row, int column) {
        boolean canPlace = false;
        for (int i = row-1; i<=row+1; i++) {
            for (int j = column-1; j<=column+1; j++) {
                if( exists(i, j) ) {
                    if (matrix[i][j] == 'e') {
                        canPlace = true;
                    }
                }
            }
        }
        return canPlace;
    }

    public boolean canMove(int row, int column) {
        boolean canMove = false;
        if (exists(row-2,column) && matrix[row-2][column] == 'e') {canMove = true;}
        else if (exists(row+2,column) && matrix[row+2][column] == 'e') {canMove = true;}
        else if (exists(row,column-2) && matrix[row][column-2] == 'e') {canMove = true;}
        else if (exists(row,column+2) && matrix[row][column+2] == 'e') {canMove = true;}
        return canMove;
    }

    public boolean exists(int row, int column) {
        boolean exists = false;
        if( 0<=row && row<=5 && 0<=column && column<=5 ){
            exists = true;
        }
        return exists;
    }

}
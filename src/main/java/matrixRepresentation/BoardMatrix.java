package matrixRepresentation;

/**
 * Class used to represent the board as a 2-dimensional array.
 */
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

    /**
     * Array used for representing the board.
     */
    private char[][] matrix;

    /**
     * Creates a {@link BoardMatrix} object.
     */
    public BoardMatrix() {
        this.matrix = new char[6][6];
        this.toStartPosition();
    }

    /**
     * Sets the matrix to starting position.
     */
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

    /**
     * Used to get the value of a specific element in matrix.
     *
     * @param row    First index of element.
     * @param column Second index of element.
     * @return The element specified by the given indexes.
     */
    public char get(int row, int column) {
        return matrix[row][column];
    }

    /**
     * Used to modify the value of a specific element in matrix.
     *
     * @param row    First index of element.
     * @param column Second index of element.
     * @param c      The new value of the element.
     */
    public void set(int row, int column, char c) {
        matrix[row][column] = c;
    }

    /**
     * Checks whether the game is over.
     *
     * @param player The current player.
     * @return False if a move is possible. True if a move is not possible.
     */
    public boolean gameOverCheck(char player) {
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
        return bool;
    }

    /**
     * Checks if a valid move is possible from a given position.
     *
     * @param row    First index of position.
     * @param column Second index of position.
     * @return True if move is possible. False if move is not possible.
     */
    public boolean movePossible(int row, int column) {
        return canPlace(row, column) || canMove(row, column);
    }

    /**
     * Checks if a valid placing is possible based on a given position.
     *
     * @param row    First index of position.
     * @param column Second index of position.
     * @return True if move is possible. False if move is not possible.
     */
    public boolean canPlace(int row, int column) {
        boolean canPlace = false;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (exists(i, j)) {
                    if (matrix[i][j] == 'e') {
                        canPlace = true;
                    }
                }
            }
        }
        return canPlace;
    }

    /**
     * Checks if a valid moving is possible from a given position.
     *
     * @param row    First index of position.
     * @param column Second index of position.
     * @return True if move is possible. False if move is not possible.
     */
    public boolean canMove(int row, int column) {
        boolean canMove = false;
        if (exists(row - 2, column) && matrix[row - 2][column] == 'e') {
            canMove = true;
        } else if (exists(row + 2, column) && matrix[row + 2][column] == 'e') {
            canMove = true;
        } else if (exists(row, column - 2) && matrix[row][column - 2] == 'e') {
            canMove = true;
        } else if (exists(row, column + 2) && matrix[row][column + 2] == 'e') {
            canMove = true;
        }
        return canMove;
    }

    /**
     * Checks if a given position is a valid part of the matrix.
     *
     * @param row    First index of position.
     * @param column Second index of position.
     * @return True if position is valid part of matrix. False if position is not a valid part of matrix.
     */
    public boolean exists(int row, int column) {
        boolean exists = false;
        if (0 <= row && row <= 5 && 0 <= column && column <= 5) {
            exists = true;
        }
        return exists;
    }

}
package matrixRepresentation;

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

    public BoardMatrix(int rows, int columns) {
        this.matrix = new char[rows][columns];
        this.toStartPosition(rows, columns);
    }

    public void toStartPosition(int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
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

}
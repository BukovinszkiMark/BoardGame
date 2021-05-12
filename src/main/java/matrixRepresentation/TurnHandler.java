package matrixRepresentation;

/**
 * Class used for handling turns.
 */
public class TurnHandler {

    /**
     * Represents the player required to move this turn.
     */
    char turn;

    /**
     * Creates a {@link TurnHandler} object.
     */
    public TurnHandler() {
        turn = 'r';
    }

    /**
     * Changes the player expected to move to the other player.
     */
    public void next() {
        if (turn == 'b') {
            turn = 'r';
        } else if (turn == 'r') {
            turn = 'b';
        }
    }

    /**
     * Returns the player expected to move this turn.
     *
     * @return The player expected to move this turn.
     */
    public char player() {
        return turn;
    }

    /**
     * Returns the opponent of the player expected to move this turn.
     *
     * @return The opponent of the player expected to move this turn.
     */
    public char enemy() {
        char enemy = '0';
        if (turn == 'r') {
            enemy = 'b';
        } else if (turn == 'b') {
            enemy = 'r';
        }
        return enemy;
    }

}
package matrixRepresentation;

public class TurnHandler {

    char turn;

    public TurnHandler() {
        turn = 'r';
    }

    public void next() {
        if (turn == 'b') {
            turn = 'r';
        }
        else if (turn == 'r') {
            turn = 'b';
        }
    }

    public char player(){
        return turn;
    }

    public char enemy() {
        char enemy = '0';
        if (turn == 'r') {enemy = 'b';}
        else if (turn == 'b') {enemy = 'r';}
        return enemy;
    }

}

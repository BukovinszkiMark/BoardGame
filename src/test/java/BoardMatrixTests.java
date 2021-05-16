import matrixRepresentation.BoardMatrix;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardMatrixTests {

    BoardMatrix testBoardMatrix;

    @BeforeEach
    public void newBoardMatrix(){
        testBoardMatrix = new BoardMatrix();
    }

    @Test
    public void toStartPositionTests(){
        testBoardMatrix.toStartPosition();

        /*
        r e e e e b
        e e e e e e
        e e e e e e
        e e e w e e
        e e e e e e
        b e e e e r
         */

        assertEquals('r' ,testBoardMatrix.get(0, 0));
        assertEquals('b' ,testBoardMatrix.get(0, 5));
        assertEquals('b' ,testBoardMatrix.get(5, 0));
        assertEquals('r' ,testBoardMatrix.get(5, 5));
        assertEquals('w' ,testBoardMatrix.get(3, 3));
        assertEquals('e' ,testBoardMatrix.get(1, 0));
        assertEquals('e' ,testBoardMatrix.get(0, 1));
        assertEquals('e' ,testBoardMatrix.get(4, 2));
    }

    @Test
    public void setAndGetTests(){
        testBoardMatrix.set(2,2,'r');
        assertEquals('r' ,testBoardMatrix.get(2, 2));
        testBoardMatrix.set(4,4,'b');
        assertEquals('b' ,testBoardMatrix.get(4, 4));
        testBoardMatrix.set(3,3,'w');
        assertEquals('w' ,testBoardMatrix.get(3, 3));
    }

    @Test
    public void checkGameOverTests(){
        assertEquals(false, testBoardMatrix.gameOverCheck('r'));
        assertEquals(false, testBoardMatrix.gameOverCheck('b'));

        //adding walls around players
        testBoardMatrix.set(1,1, 'w');
        testBoardMatrix.set(0,1, 'w');
        testBoardMatrix.set(1,0, 'w');

        testBoardMatrix.set(4,4, 'w');
        testBoardMatrix.set(4,5, 'w');
        testBoardMatrix.set(5,4, 'w');

        testBoardMatrix.set(0,4, 'w');
        testBoardMatrix.set(1,4, 'w');
        testBoardMatrix.set(1,5, 'w');

        testBoardMatrix.set(4,0, 'w');
        testBoardMatrix.set(4,1, 'w');
        testBoardMatrix.set(5,1, 'w');

        assertEquals(false, testBoardMatrix.gameOverCheck('r'));
        assertEquals(false, testBoardMatrix.gameOverCheck('b'));

        //removing red pieces
        testBoardMatrix.set(0,0, 'e');
        testBoardMatrix.set(5,5, 'e');

        assertEquals(true, testBoardMatrix.gameOverCheck('r'));
        assertEquals(false, testBoardMatrix.gameOverCheck('b'));

        //removing blue pieces
        testBoardMatrix.set(5,0, 'e');
        testBoardMatrix.set(0,5, 'e');

        assertEquals(true, testBoardMatrix.gameOverCheck('r'));
        assertEquals(true, testBoardMatrix.gameOverCheck('b'));
    }

    @Test
    public void existsTests(){
        assertEquals(true, testBoardMatrix.exists(0,0));
        assertEquals(true, testBoardMatrix.exists(5,5));
        assertEquals(true, testBoardMatrix.exists(5,0));
        assertEquals(true, testBoardMatrix.exists(0,5));
        assertEquals(false, testBoardMatrix.exists(6,0));
        assertEquals(false, testBoardMatrix.exists(0,6));
        assertEquals(false, testBoardMatrix.exists(7,68));
    }

}

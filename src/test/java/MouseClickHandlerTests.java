import matrixRepresentation.BoardMatrix;
import matrixRepresentation.MouseClickHandler;
import org.junit.jupiter.api.Test;
import ui.BoardGameController;
import ui.MainController;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MouseClickHandlerTests {

    MainController mainController;
    BoardMatrix matrix;
    MouseClickHandler mouseClickHandler;

    @Test
    public void tests(){
        mainController = new MainController(new BoardGameController());
        matrix = mainController.matrix;
        mouseClickHandler = new MouseClickHandler(mainController);

                /*
        r e e e e b
        e e e e e e
        e e e e e e
        e e e w e e
        e e e e e e
        b e e e e r
         */

        mouseClickHandler.handle(3,3);
        assertEquals('w', matrix.get(3,3));

        mouseClickHandler.handle(2,2);
        assertEquals('e', matrix.get(2,2));

        mouseClickHandler.handle(0,0);
        assertEquals('s', matrix.get(0,0));

        mouseClickHandler.handle(0,0);
        assertEquals('r', matrix.get(0,0));

        mouseClickHandler.handle(5,0);
        assertEquals('b', matrix.get(5,0));

        mouseClickHandler.handle(1,1);
        assertEquals('r', matrix.get(1,1));

        mouseClickHandler.handle(0,5);
        assertEquals('s', matrix.get(0,5));

        mouseClickHandler.handle(0,4);
        assertEquals('e', matrix.get(0,4));

        mouseClickHandler.handle(0,3);
        assertEquals('b', matrix.get(0,3));
        assertEquals('e', matrix.get(0,5));
    }

}

import matrixRepresentation.TurnHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnHandlerTests {

    TurnHandler turnHandler;

    @Test
    public void turnTests(){
        turnHandler = new TurnHandler();
        //staring turn is red by default

        assertEquals('r', turnHandler.player());
        assertEquals('b', turnHandler.enemy());

        turnHandler.next();

        assertEquals('b', turnHandler.player());
        assertEquals('r', turnHandler.enemy());

        turnHandler.next();

        assertEquals('r', turnHandler.player());
        assertEquals('b', turnHandler.enemy());
    }

}

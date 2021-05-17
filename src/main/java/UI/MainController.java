package ui;

import matrixRepresentation.BoardMatrix;
import matrixRepresentation.MouseClickHandler;
import matrixRepresentation.TurnHandler;

/**
 * Class for controlling other sub-controllers.
 */
public class MainController {

    /**
     * The controller of the board.
     */
    public BoardGameController boardGameController;

    /**
     * A matrix representation of the board using a 2-dimensional array of chars.
     */
    public BoardMatrix matrix;

    /**
     * A sub-controller object handling the results of mouse clicks.
     */
    public MouseClickHandler mouseClickHandler;

    /**
     * A sub-controller object handling the turns.
     */
    public TurnHandler turnHandler;

    /**
     * A sub-controller object handling 2 player names as input from pop-up window.
     */
    public NameInputController nameInputController;

    /**
     * A sub-controller object handling a file as input from pop-up window.
     */
    public SaveFileInputController saveFileInputController;

    /**
     * A boolean to guarantee that only one GameOverHAndler will be generated.
     */
    public boolean stopGameOverGeneration = false;

    /**
     * Creates a {@link MainController} object.
     *
     * @param boardGameController The controller of the board.
     */
    public MainController(BoardGameController boardGameController) {
        this.boardGameController = boardGameController;
        matrix = new BoardMatrix();
        turnHandler = new TurnHandler();
        mouseClickHandler = new MouseClickHandler(this);
    }

    /**
     * Asks user to choose a savefile.
     */
    public void askFile() {
        saveFileInputController = new SaveFileInputController();
        saveFileInputController.askFile();
    }

    /**
     * Asks the player names.
     */
    public void askNames() {
        nameInputController = new NameInputController();
        nameInputController.askNames();
    }

    /**
     * Checks whether a legal move can be made by current player, and initiates game over handling if necessary.
     */
    public void gameOverCheck() {
        if (matrix.gameOverCheck(turnHandler.player()) && !stopGameOverGeneration) {
            startGameOver();
        }
    }

    /**
     * Handles received mouse clicks.
     *
     * @param row The row of the click.
     * @param column The column of the click.
     */
    public void handleMouseClick(int row, int column) {
        mouseClickHandler.handle(row, column);
    }

    /**
     * Starts a game over.
     */
    public void startGameOver() {
        stopGameOverGeneration = true;
        GameOverController gameOverController = new GameOverController(turnHandler.player(), this);
    }

}

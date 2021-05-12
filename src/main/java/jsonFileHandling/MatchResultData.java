package jsonFileHandling;

/**
 * Class used to store information about the outcome of a match.
 */
public class MatchResultData {
    /**
     * Current date and time.
     */
    public String dateTime;
    /**
     * Name of the red player.
     */
    public String redName;
    /**
     * Name of the blue player.
     */
    public String blueName;
    /**
     * Score of the red player.
     */
    public int redScore;
    /**
     * Score of the blue player.
     */
    public int blueScore;
    /**
     * Color of the winner.
     */
    public char winnerColor;
    /**
     * Name of the winner.
     */
    public String winnerName;
    /**
     * Score of the winner.
     */
    public int winnerScore;

    /**
     * Creates a {@link MatchResultData} object.
     */
    public MatchResultData() {
    }

    /**
     * Creates a {@link MatchResultData} object.
     *
     * @param dateTime    The current date and time.
     * @param redName     Name of red player.
     * @param blueName    NAme of blue player.
     * @param redScore    Score of red Player.
     * @param blueScore   Score of blue player.
     * @param winnerColor Color of winner.
     * @param winnerName  Name of winner.
     * @param winnerScore Score of winner.
     */
    public MatchResultData(String dateTime, String redName, String blueName, int redScore, int blueScore, char winnerColor, String winnerName, int winnerScore) {
        this.dateTime = dateTime;
        this.redName = redName;
        this.blueName = blueName;
        this.redScore = redScore;
        this.blueScore = blueScore;
        this.winnerColor = winnerColor;
        this.winnerName = winnerName;
        this.winnerScore = winnerScore;
    }

}

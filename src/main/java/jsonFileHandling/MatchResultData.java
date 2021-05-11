package jsonFileHandling;

public class MatchResultData {
    public String dateTime;
    public String redName;
    public String blueName;
    public int redScore;
    public int blueScore;
    public char winnerColor;
    public String winnerName;
    public int winnerScore;

    public MatchResultData(){ }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRedName() {
        return redName;
    }

    public void setRedName(String redName) {
        this.redName = redName;
    }

    public String getBlueName() {
        return blueName;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

    public int getRedScore() {
        return redScore;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(int blueScore) {
        this.blueScore = blueScore;
    }

    public char getWinnerColor() {
        return winnerColor;
    }

    public void setWinnerColor(char winnerColor) {
        this.winnerColor = winnerColor;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore = winnerScore;
    }
}

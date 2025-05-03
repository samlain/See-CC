public abstract class MiniGameModel {
    String gameName;
    int points;
    boolean state;

    public abstract int addAndLosePoints(boolean state);

    public abstract boolean checkFinished();

    public int getPoints() {
        return points;
    }

    public boolean getState() {
        return state;
    }
}

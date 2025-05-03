import javax.swing.*;

public abstract class MiniGameController {
    String action;
    MiniGameModel miniGameModel;
    MiniGameView miniGameView;
    PlayerModel playerModel;
    String id;

    public String getAction(String action) {
        // TODO Replace with actual algorithm
        return action;
    }

    public void updateViewer(boolean state) {
        // TODO Replace with actual algorithm
    }

    public void givePoints(int points) {
        // TODO Replace with actual algorithm
    }

    public abstract JPanel getPanel();
}


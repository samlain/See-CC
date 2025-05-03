import javax.swing.*;
import javax.swing.ImageIcon;

/**
 * SlotModel class represents the logic for the slot machine game.
 * It handles the points system, slot values, and spin functionality.
 */
public class SlotModel extends MiniGameModel {
    int amountGambled; // the amount of points gambled
    int amountWon; // the amount of points won
    ImageIcon[] slotValues; // array holding slot machine images

    PlayerModel player; // reference to the player's model

    ImageIcon[] result; // stores the result of the slot machine spin

    /**
     * Constructs the SlotModel with an initial player model.
     * Initializes the amount gambled, amount won, and the slot images.
     *
     * @param player the player model that holds the player's points
     */
    public SlotModel(PlayerModel player) {
        this.amountGambled = 0;
        this.amountWon = 0;
        this.state = false;
        this.player = player;

        // Initialize the slot value images
        slotValues = new ImageIcon[3];
        slotValues[0] = new ImageIcon("Images/icecream.jpg");
        slotValues[1] = new ImageIcon("Images/rawchicken.jpg");
        slotValues[2] = new ImageIcon("Images/sandwich.jpg");
    }

    /**
     * Spins the slot machine and updates the result array with random images.
     *
     * @return ImageIcon array representing the result of the spin
     */
    public ImageIcon[] spinSlots() {
        ImageIcon[] result = new ImageIcon[3];
        for (int i = 0; i < 3; i++) {
            // Select a random image from the slotValues array for each slot
            result[i] = slotValues[(int) (Math.random() * slotValues.length)];
        }
        // Update points based on the result of the spin
        updatePoints(result);
        return result;
    }

    /**
     * Updates the player's points based on the result of the slot machine spin.
     * If all three slot values match, the player wins double the gambled amount.
     * Otherwise, the gambled amount is deducted from the player's points.
     *
     * @param result the result of the spin as an ImageIcon array
     */
    public void updatePoints(ImageIcon[] result) {
        // Check if all three slots match
        if (result[0] == result[1] && result[1] == result[2]) {
            amountWon = amountGambled * 2; // Double the points if all three match
            player.setPoints(amountWon, "slot");
        } else {
            // Deduct the gambled amount if the slots don't match
            amountWon = 0; // No winnings, so amountWon is 0
            player.setPoints(-amountGambled, "slot");
        }

        // Reset the amount gambled after calculating points
        amountGambled = 0;
    }

    /**
     * Adds or loses points based on the state.
     *
     * @param state the current state of the game (true if won, false if lost)
     * @return the amount of points won
     */
    @Override
    public int addAndLosePoints(boolean state) {
        return amountWon;
    }

    /**
     * Checks if the game is finished based on the player's points.
     * The game is over if the player's points are less than or equal to zero.
     *
     * @return true if the game is finished, false otherwise
     */
    @Override
    public boolean checkFinished() {
        return player.getPoints() <= 0;
    }
}

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.ImageIcon;

/**
 * Unit test class for the SlotModel class. This class tests the main functionalities
 * of the slot machine game logic such as spinning the slots, updating points,
 * and checking if the game is finished.
 */
public class SlotModelTest {

    /**
     * Tests the spinSlots method of the SlotModel class.
     * Ensures that the result of the spin is an array of three ImageIcons
     * and that all images in the result array are non-null.
     */
    @Test
    public void spinSlots() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        SlotModel slotModel = new SlotModel(player);

        ImageIcon[] result = slotModel.spinSlots();
        assertNotNull(result);  // Ensure result is not null
        assertEquals(3, result.length);  // Ensure the result array has 3 elements

        // Ensure all images in the result array are non-null
        for (ImageIcon icon : result) {
            assertNotNull(icon);
        }
    }

    /**
     * Tests the updatePoints method of the SlotModel class.
     * Simulates two cases: when all slots match and when they do not match.
     * It checks if the player's points are correctly updated based on the result of the spin.
     */
    @Test
    public void updatePoints() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        SlotModel slotModel = new SlotModel(player);

        // Test when all slots match (win case)
        ImageIcon[] matchingResult = new ImageIcon[3];
        matchingResult[0] = matchingResult[1] = matchingResult[2] = slotModel.slotValues[0];
        slotModel.amountGambled = 10;
        slotModel.updatePoints(matchingResult);
        assertEquals(20, slotModel.amountWon);  // Player should win double the points gambled
        assertEquals(120, player.getPoints());  // Player's points should increase by the winning amount

        // Test when slots don't match (lose case)
        ImageIcon[] nonMatchingResult = new ImageIcon[3];
        nonMatchingResult[0] = slotModel.slotValues[0];
        nonMatchingResult[1] = slotModel.slotValues[1];
        nonMatchingResult[2] = slotModel.slotValues[2];
        slotModel.amountGambled = 10;
        slotModel.updatePoints(nonMatchingResult);
        assertEquals(0, slotModel.amountWon);  // No points won when slots don't match
        assertEquals(110, player.getPoints());  // Player's points should decrease by the gambled amount
    }

    /**
     * Tests the addAndLosePoints method of the SlotModel class.
     * It verifies that the correct amount of points is returned for both winning and losing scenarios.
     */
    @Test
    public void addAndLosePoints() {
        Location myLoc = new Location(0, 0);
        SlotModel slotModel = new SlotModel(new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100));

        // Test winning points
        slotModel.amountWon = 50;
        int result = slotModel.addAndLosePoints(true);
        assertEquals(50, result);  // Ensure the correct points are returned for a win

        // Test losing points (no points won)
        slotModel.amountWon = 0;
        result = slotModel.addAndLosePoints(false);
        assertEquals(0, result);  // Ensure 0 points are returned for a loss
    }

    /**
     * Tests the checkFinished method of the SlotModel class.
     * It verifies whether the game is correctly marked as finished when the player's points
     * are zero or below, and not finished when the player has positive points.
     */
    @Test
    public void checkFinished() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 0);
        SlotModel slotModel = new SlotModel(player);

        // Test when the game is finished (player points <= 0)
        assertTrue(slotModel.checkFinished());  // The game should be finished when player has 0 points

        // Test when the game is not finished (player points > 0)
        player.setPoints(10, "slot");
        assertFalse(slotModel.checkFinished());  // The game should not be finished when player has positive points
    }
}

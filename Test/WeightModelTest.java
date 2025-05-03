import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test class for the WeightModel class. This class tests the core functionalities
 * of the weightlifting game model, including retrieving points, adding points, and resetting the game state.
 */
public class WeightModelTest {

    /**
     * Tests the getPoints method of the WeightModel class.
     * It verifies that the points are correctly returned based on the current game state.
     */
    @Test
    public void getPoints() {
        WeightModel weightModel = new WeightModel();
        weightModel.points = 50;

        assertEquals(50, weightModel.getPoints());  // Ensure the correct number of points is returned
    }

    /**
     * Tests the addPoints method of the WeightModel class.
     * It verifies that points are correctly added to the game and that the player's points are updated accordingly.
     */
    @Test
    public void addPoints() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        WeightModel weightModel = new WeightModel();
        weightModel.player = player;

        weightModel.points = 10;
        weightModel.addPoints(2);  // Multiply by 2

        assertEquals(20, weightModel.getPoints());  // Ensure points are updated correctly (10 * 2)
        assertEquals(120, player.getPoints());  // Ensure player's total points are updated
    }

    /**
     * Tests the resetGame method of the WeightModel class.
     * It verifies that the game state is correctly reset and that the points are set to zero.
     */
    @Test
    public void resetGame() {
        WeightModel weightModel = new WeightModel();
        weightModel.points = 100;

        weightModel.resetGame();  // Reset the game

        assertEquals(0, weightModel.getPoints());  // Ensure points are reset to 0
    }
}

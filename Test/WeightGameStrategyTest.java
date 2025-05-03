import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test class for the WeightGameStrategy class. This class tests the main functionalities
 * of the weightlifting game strategy, such as setting the difficulty, retrieving gravity and
 * multiplier values, and testing the gain and lose points methods.
 */
public class WeightGameStrategyTest {

    /**
     * Tests the setDifficulty method of the WeightGameStrategy class.
     * It verifies that the gravity and multiplier values are correctly set based on
     * the selected difficulty level ("Easy", "Medium", "Hard").
     */
    @Test
    public void setDifficulty() {
        WeightGameStrategy strategy = new WeightGameStrategy();

        // Test for "Easy" difficulty
        strategy.setDifficulty("Easy");
        assertEquals(1.0, strategy.getGravity(), 0.01);  // Ensure gravity is set to 1.0 for Easy
        assertEquals(1.0, strategy.getMultiplier(), 0.01);  // Ensure multiplier is set to 1.0 for Easy

        // Test for "Medium" difficulty
        strategy.setDifficulty("Medium");
        assertEquals(1.5, strategy.getGravity(), 0.01);  // Ensure gravity is set to 1.5 for Medium
        assertEquals(1.5, strategy.getMultiplier(), 0.01);  // Ensure multiplier is set to 1.5 for Medium

        // Test for "Hard" difficulty
        strategy.setDifficulty("Hard");
        assertEquals(2.0, strategy.getGravity(), 0.01);  // Ensure gravity is set to 2.0 for Hard
        assertEquals(2.0, strategy.getMultiplier(), 0.01);  // Ensure multiplier is set to 2.0 for Hard

        // Test for default case (invalid difficulty)
        strategy.setDifficulty("Invalid");
        assertEquals(1.0, strategy.getGravity(), 0.01);  // Ensure gravity defaults to 1.0 for invalid input
        assertEquals(1.0, strategy.getMultiplier(), 0.01);  // Ensure multiplier defaults to 1.0 for invalid input
    }

    /**
     * Tests the getGravity method of the WeightGameStrategy class.
     * It ensures that the gravity is correctly returned based on the difficulty level.
     */
    @Test
    public void getGravity() {
        WeightGameStrategy strategy = new WeightGameStrategy();

        // Test gravity for "Easy" difficulty
        strategy.setDifficulty("Easy");
        assertEquals(1.0, strategy.getGravity(), 0.01);

        // Test gravity for "Medium" difficulty
        strategy.setDifficulty("Medium");
        assertEquals(1.5, strategy.getGravity(), 0.01);

        // Test gravity for "Hard" difficulty
        strategy.setDifficulty("Hard");
        assertEquals(2.0, strategy.getGravity(), 0.01);
    }

    /**
     * Tests the getMultiplier method of the WeightGameStrategy class.
     * It ensures that the multiplier is correctly returned based on the difficulty level.
     */
    @Test
    public void getMultiplier() {
        WeightGameStrategy strategy = new WeightGameStrategy();

        // Test multiplier for "Easy" difficulty
        strategy.setDifficulty("Easy");
        assertEquals(1.0, strategy.getMultiplier(), 0.01);

        // Test multiplier for "Medium" difficulty
        strategy.setDifficulty("Medium");
        assertEquals(1.5, strategy.getMultiplier(), 0.01);

        // Test multiplier for "Hard" difficulty
        strategy.setDifficulty("Hard");
        assertEquals(2.0, strategy.getMultiplier(), 0.01);
    }

    /**
     * Tests the gainPoints method of the WeightGameStrategy class.
     * Since points are managed through the multiplier, this method always returns 0.
     * This test verifies that gainPoints returns 0 as expected.
     */
    @Test
    public void gainPoints() {
        WeightGameStrategy strategy = new WeightGameStrategy();

        // Test that gainPoints always returns 0
        assertEquals(0, strategy.gainPoints());
    }

    /**
     * Tests the losePoints method of the WeightGameStrategy class.
     * Since points are managed through the multiplier, this method always returns 0.
     * This test verifies that losePoints returns 0 as expected.
     */
    @Test
    public void losePoints() {
        WeightGameStrategy strategy = new WeightGameStrategy();

        // Test that losePoints always returns 0
        assertEquals(0, strategy.losePoints());
    }
}

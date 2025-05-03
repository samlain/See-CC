import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

/**
 * Unit test class for the TriviaGameStrategy class. This class tests the main functionalities
 * of the trivia game strategy, including retrieving questions for different difficulty levels
 * and calculating points based on the game's difficulty.
 */
public class TriviaGameStrategyTest {

    /**
     * Tests the getQuestionsForDifficulty method of the TriviaGameStrategy class.
     * It verifies that the correct set of questions and answers is returned based on
     * the selected difficulty ("Easy", "Medium", "Hard").
     */
    @Test
    public void getQuestionsForDifficulty() {
        TriviaGameStrategy triviaGameStrategy = new TriviaGameStrategy();

        // Test for "Easy" difficulty
        Map<String, String> easyQuestions = triviaGameStrategy.getQuestionsForDifficulty("Easy");
        assertNotNull(easyQuestions);  // Ensure the question map is not null
        assertEquals(5, easyQuestions.size());  // Ensure the correct number of questions are returned
        assertTrue(easyQuestions.containsKey("When was Colorado College founded?"));
        assertEquals("1874", easyQuestions.get("When was Colorado College founded?"));

        // Test for "Medium" difficulty
        Map<String, String> mediumQuestions = triviaGameStrategy.getQuestionsForDifficulty("Medium");
        assertNotNull(mediumQuestions);  // Ensure the question map is not null
        assertEquals(5, mediumQuestions.size());  // Ensure the correct number of questions are returned
        assertTrue(mediumQuestions.containsKey("What is the official name of the mascot at Colorado College?"));
        assertEquals("roccy", mediumQuestions.get("What is the official name of the mascot at Colorado College?"));

        // Test for "Hard" difficulty
        Map<String, String> hardQuestions = triviaGameStrategy.getQuestionsForDifficulty("Hard");
        assertNotNull(hardQuestions);  // Ensure the question map is not null
        assertEquals(5, hardQuestions.size());  // Ensure the correct number of questions are returned
        assertTrue(hardQuestions.containsKey("What is the full name of the founder of Colorado College?"));
        assertEquals("thomas nelson haskell", hardQuestions.get("What is the full name of the founder of Colorado College?"));
    }

    /**
     * Tests the gainPoints method of the TriviaGameStrategy class.
     * It verifies that the correct number of points is gained for each difficulty level
     * ("Easy", "Medium", "Hard").
     */
    @Test
    public void gainPoints() {
        TriviaGameStrategy triviaGameStrategy = new TriviaGameStrategy();

        // Test gain points for "Easy" difficulty
        triviaGameStrategy.getQuestionsForDifficulty("Easy");
        assertEquals(10, triviaGameStrategy.gainPoints());  // Easy should return 10 points for a correct answer

        // Test gain points for "Medium" difficulty
        triviaGameStrategy.getQuestionsForDifficulty("Medium");
        assertEquals(20, triviaGameStrategy.gainPoints());  // Medium should return 20 points for a correct answer

        // Test gain points for "Hard" difficulty
        triviaGameStrategy.getQuestionsForDifficulty("Hard");
        assertEquals(30, triviaGameStrategy.gainPoints());  // Hard should return 30 points for a correct answer
    }

    /**
     * Tests the losePoints method of the TriviaGameStrategy class.
     * It verifies that the correct number of points is lost for each difficulty level
     * ("Easy", "Medium", "Hard").
     */
    @Test
    public void losePoints() {
        TriviaGameStrategy triviaGameStrategy = new TriviaGameStrategy();

        // Test lose points for "Easy" difficulty
        triviaGameStrategy.getQuestionsForDifficulty("Easy");
        assertEquals(5, triviaGameStrategy.losePoints());  // Easy should lose 5 points for an incorrect answer

        // Test lose points for "Medium" difficulty
        triviaGameStrategy.getQuestionsForDifficulty("Medium");
        assertEquals(10, triviaGameStrategy.losePoints());  // Medium should lose 10 points for an incorrect answer

        // Test lose points for "Hard" difficulty
        triviaGameStrategy.getQuestionsForDifficulty("Hard");
        assertEquals(15, triviaGameStrategy.losePoints());  // Hard should lose 15 points for an incorrect answer
    }
}

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test class for the TriviaModel class. This class tests the main functionalities
 * of the trivia game model, such as setting the difficulty, retrieving the current question and answer,
 * updating points, and progressing through questions.
 */
public class TriviaModelTest {

    /**
     * Tests the setDifficulty method of the TriviaModel class.
     * It verifies that when the difficulty is set, the questions are updated
     * accordingly and the current question is properly initialized.
     */
    @Test
    public void setDifficulty() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        assertNotNull(triviaModel.getCurrentQuestion()); // Ensure questions are set after difficulty
    }

    /**
     * Tests the getCurrentQuestion method of the TriviaModel class.
     * It ensures that the current question returned corresponds to the selected difficulty.
     */
    @Test
    public void getCurrentQuestion() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        String currentQuestion = triviaModel.getCurrentQuestion();
        assertNotNull(currentQuestion);  // Ensure a valid question is returned
        assertEquals("When was Colorado College founded?", currentQuestion); // Example question
    }

    /**
     * Tests the getAnswer method of the TriviaModel class.
     * It ensures that the correct answer is returned for the current question.
     */
    @Test
    public void getAnswer() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        String answer = triviaModel.getAnswer();
        assertNotNull(answer);
        assertEquals("1874", answer);  // Example answer for the question "When was Colorado College founded?"
    }

    /**
     * Tests the gainAndLosePoints method of the TriviaModel class.
     * It verifies that points are correctly added or deducted based on whether the answer is correct.
     */
    @Test
    public void gainAndLosePoints() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        triviaModel.gainAndLosePoints(true);
        assertEquals(10, triviaModel.getCurrentGamePoints());  // Assuming 10 points for a correct answer

        triviaModel.gainAndLosePoints(false);
        assertEquals(5, triviaModel.getCurrentGamePoints());  // Deduct 5 points for incorrect answer
    }

    /**
     * Tests the getCurrentGamePoints method of the TriviaModel class.
     * It ensures that the player's current game points are correctly tracked and returned.
     */
    @Test
    public void getCurrentGamePoints() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        triviaModel.gainAndLosePoints(true);
        assertEquals(10, triviaModel.getCurrentGamePoints());
    }

    /**
     * Tests the nextQuestion method of the TriviaModel class.
     * It ensures that the game moves to the next question correctly and returns the appropriate question.
     */
    @Test
    public void nextQuestion() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        triviaModel.nextQuestion();
        String nextQuestion = triviaModel.getCurrentQuestion();
        assertNotNull(nextQuestion);
        assertEquals("What animal is the mascot at Colorado College?", nextQuestion); // Example next question
    }

    /**
     * Tests the checkFinished method of the TriviaModel class.
     * It verifies that the game correctly identifies when all questions have been answered.
     */
    @Test
    public void checkFinished() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        // Progress through all questions
        triviaModel.nextQuestion();
        triviaModel.nextQuestion();
        triviaModel.nextQuestion();
        triviaModel.nextQuestion();
        triviaModel.nextQuestion(); // Now at the last question
        assertTrue(triviaModel.checkFinished());  // Ensure game is marked as finished
    }

    /**
     * Tests the resetQuestions method of the TriviaModel class.
     * It ensures that the game resets the question index correctly, returning to the first question.
     */
    @Test
    public void resetQuestions() {
        Location myLoc = new Location(0, 0);
        PlayerModel player = new PlayerModel("sam", "samuel", "lain", "tiger", "cs", myLoc, 100);
        TriviaModel triviaModel = new TriviaModel(player);
        triviaModel.setDifficulty("Easy");

        triviaModel.nextQuestion(); // Move to the second question
        triviaModel.resetQuestions(); // Reset to the first question

        String firstQuestion = triviaModel.getCurrentQuestion();
        assertEquals("When was Colorado College founded?", firstQuestion); // Ensure the game resets to the first question
    }
}

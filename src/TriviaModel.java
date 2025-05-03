import java.util.Map;

/**
 * The TriviaModel class handles the game logic for a trivia game.
 * It manages the current questions, the player's score, and the
 * game strategy (points calculation and question retrieval) based on difficulty.
 */
public class TriviaModel {
    private Map<String, String> qAndA; // Map to store questions and answers
    private int questionIndex; // Current index of the question being asked
    private String[] keys; // Array of question keys (questions)
    private TriviaGameStrategy strategy; // Strategy for the trivia game
    private PlayerModel player;  // Instance of PlayerModel to track player's points
    private int currentGamePoints; // Points earned in the current game session

    /**
     * Constructor for TriviaModel that initializes the game with a player.
     *
     * @param player The PlayerModel object used to track the player's points.
     */
    public TriviaModel(PlayerModel player) {
        this.player = player;  // Initialize with the player instance
        this.strategy = new TriviaGameStrategy();
        this.questionIndex = 0;
        this.currentGamePoints = 0;
    }

    /**
     * Sets the difficulty level of the trivia game and updates the set of questions accordingly.
     *
     * @param difficulty A string representing the difficulty level (e.g., "Easy", "Medium", "Hard").
     */
    public void setDifficulty(String difficulty) {
        updateQuestions(difficulty);
        keys = qAndA.keySet().toArray(new String[0]);
        questionIndex = 0;
    }

    /**
     * Updates the questions for the trivia game based on the difficulty.
     *
     * @param difficulty The difficulty level used to retrieve the relevant set of questions.
     */
    private void updateQuestions(String difficulty) {
        this.qAndA = strategy.getQuestionsForDifficulty(difficulty);
    }

    /**
     * Gets the current question to be displayed to the player.
     *
     * @return A string containing the current question.
     */
    public String getCurrentQuestion() {
        return keys[questionIndex];
    }

    /**
     * Gets the answer corresponding to the current question.
     *
     * @return A string containing the answer to the current question.
     */
    public String getAnswer() {
        return qAndA.get(keys[questionIndex]);
    }

    /**
     * Updates the player's points based on whether their answer was correct or incorrect.
     *
     * @param correct A boolean indicating if the player's answer was correct.
     */
    public void gainAndLosePoints(boolean correct) {
        if (correct) {
            currentGamePoints += strategy.gainPoints();
        } else {
            currentGamePoints -= strategy.losePoints();
        }
    }

    /**
     * Gets the player's current points for the ongoing game session.
     *
     * @return An integer representing the current points of the game session.
     */
    public int getCurrentGamePoints() {
        return currentGamePoints;
    }

    /**
     * Moves to the next question in the trivia game.
     */
    public void nextQuestion() {
        if (questionIndex < keys.length - 1) {
            questionIndex++;
        }
    }

    /**
     * Checks if all questions in the game have been answered.
     *
     * @return A boolean indicating if all questions are answered.
     */
    public boolean checkFinished() {
        return questionIndex >= keys.length - 1;
    }

    /**
     * Resets the trivia game by resetting the question index to the start.
     */
    public void resetQuestions() {
        questionIndex = 0;
    }
}

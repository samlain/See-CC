import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The TriviaGameStrategy class implements the Strategy interface and handles
 * the trivia game logic related to questions and point calculation based on difficulty.
 * It stores different sets of questions and answers for easy, medium, and hard levels,
 * and defines the points gained or lost for each difficulty.
 */
public class TriviaGameStrategy implements Strategy {
    private Map<String, String> easyQAndA, mediumQAndA, hardQAndA;  // Maps to store questions and answers by difficulty
    private int gainPoints;  // Points gained for a correct answer
    private int losePoints;  // Points lost for an incorrect answer

    /**
     * Constructor for TriviaGameStrategy.
     * Initializes the question and answer sets for each difficulty level.
     */
    public TriviaGameStrategy() {
        triviaQuestionsAndAnswers();  // Initialize the question and answer sets
    }

    /**
     * Initializes the trivia questions and answers for easy, medium, and hard difficulties.
     * The questions and answers are stored in LinkedHashMaps to preserve insertion order.
     */
    private void triviaQuestionsAndAnswers() {
        easyQAndA = new LinkedHashMap<>();
        easyQAndA.put("When was Colorado College founded?", "1874");
        easyQAndA.put("What animal is the mascot at Colorado College?", "tiger");
        easyQAndA.put("What city is Colorado College located?", "colorado springs");
        easyQAndA.put("What state is Colorado College located?", "colorado");
        easyQAndA.put("What is the full name of the country where Colorado College is located?", "united states of america");

        mediumQAndA = new LinkedHashMap<>();
        mediumQAndA.put("What is the official name of the mascot at Colorado College?", "roccy");
        mediumQAndA.put("What conference does the majority of sports at Colorado College participate in?", "scac");
        mediumQAndA.put("What famous natural landmark is closest to Colorado College?", "pikes peek");
        mediumQAndA.put("What is the one men's D1 sport at Colorado College?", "hockey");
        mediumQAndA.put("Who is the current president at Colorado College?", "manya whitaker");

        hardQAndA = new LinkedHashMap<>();
        hardQAndA.put("What is the full name of the founder of Colorado College?", "thomas nelson haskell");
        hardQAndA.put("Which famous horror/thriller author taught at Colorado College?", "stephen king");
        hardQAndA.put("Which former U.S. President served as a member of the Board of Trustees at Colorado College?", "franklin roosevelt");
        hardQAndA.put("What is Colorado College's motto in Latin?", "scientia et disciplina");
        hardQAndA.put("What is Colorado College's motto?", "learning through hard work");
    }

    /**
     * Returns a set of trivia questions and answers based on the selected difficulty level.
     * Also sets the points gained or lost based on the difficulty.
     *
     * @param difficulty The difficulty level ("Easy", "Medium", "Hard").
     * @return A map of questions and answers for the selected difficulty.
     */
    public Map<String, String> getQuestionsForDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                gainPoints = 10;
                losePoints = 5;
                return easyQAndA;
            case "Medium":
                gainPoints = 20;
                losePoints = 10;
                return mediumQAndA;
            case "Hard":
                gainPoints = 30;
                losePoints = 15;
                return hardQAndA;
            default:
                return easyQAndA;
        }
    }

    /**
     * Returns the points to be gained for a correct answer based on the difficulty level.
     *
     * @return The number of points gained for a correct answer.
     */
    @Override
    public int gainPoints() {
        return gainPoints;
    }

    /**
     * Returns the points to be lost for an incorrect answer based on the difficulty level.
     *
     * @return The number of points lost for an incorrect answer.
     */
    @Override
    public int losePoints() {
        return losePoints;
    }
}

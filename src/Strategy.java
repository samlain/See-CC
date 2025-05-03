/**
 * The Strategy interface defines the contract for point calculation in games.
 * It provides methods to determine the points gained for correct answers and
 * the points lost for incorrect answers.
 */
public interface Strategy {

    /**
     * Calculates and returns the points gained for a correct action (e.g., a correct trivia answer).
     *
     * @return The number of points to be gained.
     */
    int gainPoints();

    /**
     * Calculates and returns the points lost for an incorrect action (e.g., a wrong trivia answer).
     *
     * @return The number of points to be lost.
     */
    int losePoints();
}

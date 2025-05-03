/**
 * The WeightModel class handles the data and logic for the weightlifting game.
 * It manages the player's points and provides methods for updating and resetting the game state.
 */
public class WeightModel {
    public int points;  // Points accumulated during the game
    PlayerModel player;  // Reference to the PlayerModel for tracking the player's total points

    /**
     * Gets the current points accumulated in the game.
     *
     * @return The current number of points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the game total based on the multiplier applied when the player is in the zone.
     * The multiplier is determined by the game's difficulty level.
     *
     * @param multipliedPoints The points to be added after applying the multiplier.
     */
    public void addPoints(int multipliedPoints) {
        points = points * multipliedPoints;  // Multiply and add the points to the total points
        player.setPoints(points, "weight");  // Update the global player points (if applicable)
    }

    /**
     * Resets the game state by clearing the points.
     * This method is used when starting a new game.
     */
    public void resetGame() {
        points = 0;  // Reset the points to zero for a new game session
    }
}

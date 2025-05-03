/**
 * The WeightGameStrategy class implements the Strategy interface and is responsible
 * for setting up game parameters such as gravity and points multiplier based on the
 * selected difficulty level in the weightlifting game.
 */
public class WeightGameStrategy implements Strategy {
    private double gravity;     // The gravity factor that affects the barbell's movement
    private double multiplier;  // The multiplier applied to points based on difficulty

    /**
     * Constructor for WeightGameStrategy.
     * Initializes the default gravity and multiplier values for the game.
     */
    public WeightGameStrategy() {
        // Initial default values
        this.gravity = 1.0;
        this.multiplier = 1.0;
    }

    /**
     * Sets the difficulty level and adjusts the gravity and points multiplier accordingly.
     *
     * @param difficulty The difficulty level selected ("Easy", "Medium", "Hard").
     */
    public void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                this.gravity = 1.0;
                this.multiplier = 1.0;  // 10x points for Easy
                break;
            case "Medium":
                this.gravity = 1.5;
                this.multiplier = 1.5;  // 15x points for Medium
                break;
            case "Hard":
                this.gravity = 2.0;
                this.multiplier = 2.0;  // 20x points for Hard
                break;
            default:
                this.gravity = 1.0;  // Default to Easy
                this.multiplier = 1.0;
                break;
        }
    }

    /**
     * Returns the gravity factor for the game.
     * The gravity factor affects how fast the barbell falls in the weightlifting game.
     *
     * @return The gravity value based on the selected difficulty.
     */
    public double getGravity() {
        return gravity;
    }

    /**
     * Returns the points multiplier based on the difficulty selected.
     * The multiplier is used to calculate how many points the player earns during the game.
     *
     * @return The points multiplier based on the selected difficulty.
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Not used in this game strategy. Points are managed through the multiplier.
     * This method exists to fulfill the Strategy interface contract.
     *
     * @return Always returns 0 as points are handled by the multiplier.
     */
    @Override
    public int gainPoints() {
        return 0;  // Not used here, points are handled by the multiplier
    }

    /**
     * Not used in this game strategy. Points are managed through the multiplier.
     * This method exists to fulfill the Strategy interface contract.
     *
     * @return Always returns 0 as losing points is not part of this strategy.
     */
    @Override
    public int losePoints() {
        return 0;
    }
}

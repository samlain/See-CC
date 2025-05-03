import java.util.Map;

/**
 * The ZamboniMazeModel class is responsible for handling the logic and data related to the maze game.
 * It manages the player's position, the current maze layout, and the scoring system.
 */
public class ZamboniMazeModel {
    private int[][] currentMaze;
    private int playerX, playerY;  // Player's position
    private int score;
    private ZamboniMazeGameStrategy strategy;
    private int goalX, goalY;
    PlayerModel player;

    public ZamboniMazeModel() {
        strategy = new ZamboniMazeGameStrategy();
    }

    /**
     * Set up the maze based on the selected difficulty.
     *
     * @param difficulty The difficulty level ("Easy", "Medium", or "Hard").
     */
    public void setMaze(String difficulty) {
        currentMaze = strategy.getMaze(difficulty);
        resetPlayerPosition();

        // Set goal position to the bottom right of the maze (where the red square is)
        goalX = currentMaze.length - 2;
        goalY = currentMaze[0].length - 2;

        score = 0;
    }

    /**
     * Check if the player has reached the goal.
     *
     * @return True if the player is at the goal position.
     */
    public boolean isAtGoal() {
        return playerX == goalX && playerY == goalY;
    }



    /**
     * Get the current maze layout.
     *
     * @return The 2D array representing the maze layout.
     */
    public int[][] getCurrentMaze() {
        return currentMaze;
    }

    /**
     * Get the player's current X position.
     *
     * @return The X coordinate of the player's position.
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Get the player's current Y position.
     *
     * @return The Y coordinate of the player's position.
     */
    public int getPlayerY() {
        return playerY;
    }

    /**
     * Reset the player to the starting position.
     */
    public void resetPlayerPosition() {
        playerX = 1;  // Starting point (assuming 1,1 is the first available position)
        playerY = 1;
    }

    /**
     * Move the player based on the delta in X and Y.
     *
     * @param deltaX The change in X position.
     * @param deltaY The change in Y position.
     * @return True if the move is valid, false otherwise.
     */
    public boolean movePlayer(int deltaX, int deltaY) {
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;
        if (isValidMove(newX, newY)) {
            playerX = newX;
            playerY = newY;
            return true;
        }
        return false;
    }

    /**
     * Check if the move is valid.
     *
     * @param x The X coordinate of the position to move to.
     * @param y The Y coordinate of the position to move to.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValidMove(int x, int y) {
        return currentMaze[x][y] == 0;  // 0 means path, 1 means wall
    }

    /**
     * Increase the player's score based on the strategy's gain points.
     */
    public void increaseScore() {
        player.setPoints(strategy.gainPoints(), "zamboni");
    }

    /**
     * Get the current score of the player.
     *
     * @return The player's score.
     */
    public int getScore() {
        return player.getPoints();
    }
}
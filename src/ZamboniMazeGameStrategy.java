import java.util.Map;

/**
 * The ZamboniMazeGameStrategy class is responsible for providing different maze layouts
 * and point rules based on the selected difficulty.
 */
public class ZamboniMazeGameStrategy implements Strategy {
    private int[][] easyMaze = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
    };

    private int[][] mediumMaze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private int[][] hardMaze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int gainPoints;
    private int losePoints;

    /**
     * Get the maze layout based on the selected difficulty.
     *
     * @param difficulty The difficulty level ("Easy", "Medium", or "Hard").
     * @return The 2D array representing the maze layout.
     */
    public int[][] getMaze(String difficulty) {
        switch (difficulty) {
            case "Easy":
                gainPoints = 50;
                losePoints = 0;
                return easyMaze;
            case "Medium":
                gainPoints = 100;
                losePoints = 0;
                return mediumMaze;
            case "Hard":
                gainPoints = 150;
                losePoints = 0;
                return hardMaze;
            default:
                return easyMaze;
        }
    }

    @Override
    public int gainPoints() {
        return gainPoints;
    }

    @Override
    public int losePoints() {
        return losePoints;
    }
}
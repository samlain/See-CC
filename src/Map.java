import java.util.Random;

/**
 * Represents a 2D grid map where marks can be added or removed at specific locations.
 * The map is initialized to a blank state, and operations can be performed to manipulate marks on the grid.
 */
public class Map<S, I extends Number> {
    private String[][] data;
    private int width;
    private int height;

    /**
     * Constructs a Map with the specified width and height.
     * Initializes the map to have empty strings in all cells.
     *
     * @param width  The width of the map.
     * @param height The height of the map.
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new String[width][height];

        initialize();
    }

    /**
     * Initializes the map by setting all cells to empty strings.
     */
    public void initialize() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = "";
            }
        }
    }

    /**
     * Returns the 2D array representing the map data.
     *
     * @return A 2D array of strings representing the map.
     */
    public String[][] getData() {
        return data;
    }

    /**
     * Adds a mark at the specified location on the map.
     * If the location is out of bounds, an error message is printed.
     *
     * @param location The location where the mark should be added.
     * @param mark     The mark to be added at the specified location.
     */
    public void addMark(Location location, String mark) {
        data[location.getX()][location.getY()] = mark;
    }

    /**
     * Checks if a location is in bounds
     * @param location the spot to check
     * @return true if in bounds, false otherwise
     */
    public boolean isInBounds(Location location) {
        return (location.getX() >= 0 && location.getX() < width && location.getY() >= 0 && location.getY() < height);
    }

    /**
     * Generates a random, empty in-bounds location
     * @return the new random location
     */
    public Location randomLocation() {
        Random random = new Random();
        // Generate random x and y coordinates within map bounds
        int randomX = random.nextInt(width);  // Random value between 0 and width-1
        int randomY = random.nextInt(height); // Random value between 0 and height-1
        if (data[randomX][randomY] == "") {
            return new Location(randomX, randomY);
        } else {
            return randomLocation();
        }
    }

    /**
     * Clears the map by resetting all cells to empty strings.
     */
    public void clear() {
        this.data = new String[width][height];
        initialize();
    }

    /**
     * Prints the map to the console in a readable format.
     */
    public void print() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

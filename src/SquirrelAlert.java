import java.util.Random;

/**
 * The SquirrelAlert class extends AbstractAlert to provide a specific type of alert
 * for displaying a squirrel image and awarding points to the player.
 */
public class SquirrelAlert extends AbstractAlert {
    /**
     * The number of points randomly awarded when the squirrel alert is dismissed.
     */
    int randomPoints;

    /**
     * Constructs a SquirrelAlert instance with a default "OK" button.
     */
    public SquirrelAlert() {
        super("OK");
    }

    /**
     * Handles the action when the alert's action button is pressed.
     * Sets the points earned from the squirrel alert to the player and hides the alert.
     */
    @Override
    protected void onActionButtonPressed() {
        System.out.println("Squirrel alert dismissed.");
        PlayerModel.shared.setPoints(randomPoints, "squirrel");
        setVisible(false); // Hide the alert
    }

    /**
     * Displays a squirrel alert with a specific squirrel number.
     * Generates random points within a defined range and shows the alert with an image
     * and a message that includes the points awarded.
     *
     * @param squirrelNumber The number of the squirrel image to display.
     *                       If the number is 6, a special message is shown.
     */
    public void showSquirrelAlert(int squirrelNumber) {
        Random random = new Random();
        int min = -20;
        int max = 50;
        randomPoints = random.nextInt((max - min) + 1) + min;

        String squirrelImageFile = "Images/Squirrels/squirrel" + squirrelNumber + ".png";
        String message = squirrelNumber == 6
                ? "<h1>You found L Song Richardson.</h1>"
                : "<h1>You found a squirrel.</h1>";

        // Add the points gained to the message
        message += "<p>You gained " + randomPoints + " points!</p>";

        showAlert(squirrelImageFile, message);
    }
}

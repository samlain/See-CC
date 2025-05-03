import java.util.Random;

/**
 * The PlayerCollisionAlert class extends AbstractAlert to provide an alert
 * for player collisions where the current player can take points from another player.
 */
public class PlayerCollisionAlert extends AbstractAlert {

    /**
     * The player from whom points can be taken.
     */
    private PlayerModel otherPlayer;

    /**
     * The controller managing the map and player interactions.
     */
    private MapController controller;

    /**
     * The number of points randomly awarded when robbing another player.
     */
    int randomPoints;

    /**
     * Constructs a PlayerCollisionAlert instance with a default action button
     * and initializes the number of points that can be robbed.
     *
     * @param mapController The MapController instance managing the game map and interactions.
     */
    public PlayerCollisionAlert(MapController mapController) {
        super("Take their points!");
        Random random = new Random();
        randomPoints = random.nextInt((30 - 5) + 1) + 5;
        this.controller = mapController;
    }

    /**
     * Handles the action when the alert's action button is pressed.
     * Takes points from the other player, updates their location on the map,
     * updates the points of both players, and hides the alert.
     */
    @Override
    protected void onActionButtonPressed() {
        System.out.println("You took points from " + otherPlayer.getName() + "!");
        Database.shared.updateMap(otherPlayer.getUsername(), otherPlayer.getLocation().getX(), otherPlayer.getLocation().getY(), 20);
        PlayerModel.shared.setPoints(randomPoints, "robbed");
        otherPlayer.setPoints(-randomPoints, "robbed");
        controller.setRandomPlayer();
        setVisible(false); // Hide the alert
    }

    /**
     * Displays the collision alert with details about the player whose points can be robbed.
     * Sets the message to include the player's information and the number of points that can be taken.
     *
     * @param player The PlayerModel instance representing the player from whom points can be robbed.
     */
    public void showCollisionAlert(PlayerModel player) {
        this.otherPlayer = player;
        String message = "<h3>" + player.getUsername().toUpperCase() + "</h3>"
                + "<p>" + player.getName() + "</p>"
                + "<p>" + player.getMajor() + " major</p>"
                + "<h5>You can rob " + randomPoints + " of their points!</h5>";
        showAlert("Images/Icons/" + player.getIcon() + ".png", message);
    }

}

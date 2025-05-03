import javax.swing.*;

/**
 * Manages interactions between the player and the map, including movement, building interactions, and events related to the squirrel and random players.
 * Controls the view of the map and handles events when the player moves or interacts with buildings and other elements.
 */
public class MapController {
    private BuildingController[] buildings;
    private MapView mapView;
    private BuildingController selectedBuilding;
    private Location squirrel;
    private PlayerModel randomPlayer;

    /**
     * Constructs a MapController with the specified array of building controllers.
     * Initializes the map view associated with this controller and sets up a random squirrel location.
     *
     * @param buildings Array of building controllers in the game.
     */
    public MapController(BuildingController[] buildings) {
        this.buildings = buildings;
        this.mapView = new MapView(buildings, this);
        this.squirrel = mapView.getBuildingsMap().randomLocation();
        this.mapView.setupMap();
    }

    /**
     * Focuses on the map view and sets a random player, updating the map setup.
     */
    public void focus(){
        mapView.focus();
        this.setRandomPlayer();
        this.mapView.setupMap();
    }

    /**
     * Moves the player to the specified location, updates the map view, and handles collisions with buildings and the squirrel.
     * Displays an interact button if the player is near a building, and checks for collisions with the squirrel and random player.
     *
     * @param location The new location for the player.
     */
    public void movePlayer(Location location) {
        location = ensureLocationWithinBounds(location);
        PlayerModel.shared.move(location);
        mapView.markPlayer(PlayerModel.shared);

        if (checkBuildingCollisions(location)) {
            BuildingController building = getNearbyBuilding(location);
            if (building != null) {
                mapView.showInteractButton(building.model.name());
            }
        } else {
            mapView.hideInteractButton();
        }

        checkSquirrelCollision(location);
        checkRandomPlayerCollision(location);
    }

    /**
     * Ensures the player's location stays within the map bounds. If the location is out of bounds, it wraps around to the opposite side.
     *
     * @param location The desired location of the player.
     * @return The adjusted location within the map bounds.
     */
    private Location ensureLocationWithinBounds(Location location) {
        if (!mapView.getPlayerMap().isInBounds(location)) {
            int newX = location.getX();
            int newY = location.getY();
            int mapWidth = mapView.getPlayerMap().getWidth();
            int mapHeight = mapView.getPlayerMap().getHeight();

            if (newX < 0) newX = mapWidth - 1;
            else if (newX >= mapWidth) newX = 0;

            if (newY < 0) newY = mapHeight - 1;
            else if (newY >= mapHeight) newY = 0;

            location = new Location(newX, newY);
        }
        return location;
    }

    /**
     * Selects a random player from the database, excluding the current player,
     * and places them at a random location on the map.
     */
    public void setRandomPlayer() {
        PlayerModel p = Database.shared.getLowestRobbedPlayer();
        if (!p.getUsername().equals(PlayerModel.shared.getUsername())) {
            this.randomPlayer = p;
            randomPlayer.move(mapView.getPlayerMap().randomLocation());
        } else {
            setRandomPlayer();
        }
    }

    /**
     * Checks if the player collides with the random player and updates the map view if a collision occurs.
     * Sets a new random player after a collision.
     * @param location The current location of the player.
     */
    private void checkRandomPlayerCollision(Location location) {
        if (location.equals(randomPlayer.getLocation())) {
            System.out.println("Collided with " + randomPlayer.getName());
            mapView.showPlayerCollision(randomPlayer);
            setRandomPlayer();
        }
    }

    /**
     * Checks if the player collides with any building. If a collision is detected, updates the selected building and shows the interact button.
     *
     * @param location The current location of the player.
     * @return true if the player collides with a building; false otherwise.
     */
    private boolean checkBuildingCollisions(Location location) {
        for (BuildingController building : buildings) {
            if (building.model.location().equals(location)) {
                System.out.println("Collided with " + building.model.name());
                selectedBuilding = building;
                return true;
            }
        }
        selectedBuilding = null;
        return false;
    }

    /**
     * Gets the building controller for the building that the player is currently near.
     *
     * @param location The current location of the player.
     * @return The building controller if the player is near a building; null otherwise.
     */
    private BuildingController getNearbyBuilding(Location location) {
        for (BuildingController building : buildings) {
            if (building.model.location().equals(location)) {
                return building;
            }
        }
        return null;
    }

    /**
     * Checks if the player collides with the squirrel. If a collision occurs, displays the squirrel alert and moves the squirrel to a new random location.
     *
     * @param location The current location of the player.
     */
    private void checkSquirrelCollision(Location location) {
        if (location.equals(squirrel)) {
            mapView.showSquirrel();
            squirrel = mapView.getBuildingsMap().randomLocation();
        }
    }

    /**
     * Handles the action of entering a building if a building has been selected.
     * Notifies the game controller to handle the building entry.
     */
    public void enterBuilding() {
        if (selectedBuilding != null) {
            System.out.println("Entering " + selectedBuilding.model.name());
            GameController.controller.enterBuilding(selectedBuilding);
        }
    }

    /**
     * Returns the JPanel containing the map view, which can be used in a JFrame.
     *
     * @return The JPanel containing the map view.
     */
    public JPanel getPanel() {
        return mapView;
    }

    /**
     * Gets the current location of the squirrel.
     *
     * @return The location of the squirrel.
     */
    public Location getSquirrel() {
        return squirrel;
    }

    /**
     * Gets the current random player.
     *
     * @return The random player.
     */
    public PlayerModel getRandomPlayer() {
        return randomPlayer;
    }

    /**
     * Resets the map view and sets up the map again.
     */
    public void enterMap() {
        mapView.setupMap();
    }
}

import java.util.ArrayList;
import java.util.List;
/**
 * This class will do the main game logic. Control the game controller.
 */
public class GameController {
    GameViewer gameViewer;
    List<PlayerModel> players;

    static public GameController controller = new GameController();

    /**
     * The constructor
     */
    public GameController(){
        // Each time a new username appear, the arraylist will be appended
        players = new ArrayList<>();
    }

    /**
     * Setting up the buildings and GameViewer.
     * Also using BuildingFactory to make the buildings(4). And connect to the mapController.
     * return: void
     */
    public void enterGame() {
        BuildingFactory myFactory = new BuildingFactory();
        String[] buildingNames = {"gym", "library", "worner"};
        BuildingController[] buildings = new BuildingController[3];
        for (int i = 0; i < buildings.length; i++){
            buildings[i] = myFactory.buildingBuildings(buildingNames[i], PlayerModel.shared);
        }
        MapController controller = new MapController(buildings);
        GameController.controller.setGameViewer(new GameViewer(controller, buildings));
        GameController.controller.showPanel("map");
    }

    /**
     * This method is to set up the gameViewer
     * @param gameViewer
     */
    public void setGameViewer(GameViewer gameViewer) {
        this.gameViewer = gameViewer;
    }

    /**
     * This method is for showing up the panels
     * @param name
     */
    public void showPanel(String name) {
        gameViewer.showPanel(name);
    }

    /**
     * This method is for calling the logic of showing up the buildings' panels
     * @param building The building player choose to enter
     */
    public void enterBuilding(BuildingController building) {
        showPanel(building.model.name);
        building.setStage();
    }

    /**
     * This method is for showing up the map panel, as the main map of game
     */
    public void showMap() {
        showPanel("map");
        gameViewer.mapController.enterMap(); //bad java
    }


}



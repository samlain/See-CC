import org.junit.Assert;
import org.junit.Test;

public class MapControllerTest {

    @Test
    public void focus() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        mapController.focus();
        Assert.assertNotNull(mapController.getRandomPlayer());
    }

    @Test
    public void movePlayer() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        Location initialLocation = new Location(0, 0);
        mapController.setRandomPlayer();
        mapController.movePlayer(initialLocation);
        Assert.assertEquals(PlayerModel.shared.getLocation(), initialLocation);
    }

    @Test
    public void setRandomPlayer() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        mapController.setRandomPlayer();
        Assert.assertNotNull(mapController.getRandomPlayer());
    }

    @Test
    public void enterBuilding() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[1];
        buildings[0] = new GymController(PlayerModel.shared);
        MapController mapController = new MapController(buildings);
        mapController.setRandomPlayer();
        mapController.movePlayer(new Location(1, 1));
        mapController.enterBuilding();
        Assert.assertNotNull(mapController.getPanel());
    }

    @Test
    public void getPanel() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        Assert.assertNotNull(mapController.getPanel());
    }

    @Test
    public void getSquirrel() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        Assert.assertNotNull(mapController.getSquirrel());
    }

    @Test
    public void getRandomPlayer() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        mapController.setRandomPlayer();
        Assert.assertNotNull(mapController.getRandomPlayer());
    }

    @Test
    public void enterMap() {
        PlayerModel.shared = new PlayerModel("Test User", "test");
        BuildingController[] buildings = new BuildingController[0];
        MapController mapController = new MapController(buildings);
        mapController.enterMap();
        Assert.assertNotNull(mapController.getPanel());
    }
}

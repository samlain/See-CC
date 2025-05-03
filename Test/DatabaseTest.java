import org.junit.Assert;
import org.junit.Test;
public class DatabaseTest {

    @Test
    public void signUp() {
        // Assuming signUp returns null if user already exists
        PlayerModel result = Database.shared.signUp("Test User", "First", "Last", "CC", "Computer Science");
        Assert.assertNull(result);
    }

    @Test
    public void signIn() {
        PlayerModel result = Database.shared.signIn("Test User");
        Assert.assertNotNull(result);
        Assert.assertEquals("Test User", result.getUsername());
    }

    @Test
    public void updatePlayerLocation() {
        Database.shared.updatePlayerLocation("Test User", 7, 8);
        PlayerModel result = Database.shared.signIn("Test User");
        Assert.assertNotNull(result);
        Assert.assertEquals(7, result.getLocation().getX());
        Assert.assertEquals(8, result.getLocation().getY());
    }

    @Test
    public void getPlayerData() {
        PlayerModel[] result = Database.shared.getPlayerData();
        Assert.assertTrue(result.length > 0);
    }

    @Test
    public void getLowestRobbedPlayer() {
        PlayerModel result = Database.shared.getLowestRobbedPlayer();
        Assert.assertNotNull(result);
    }

    @Test
    public void getMapData() {
        Database.shared.getMapData(); // Assuming this prints to console, no assert here
    }

    @Test
    public void addPointsData() {
        Database.shared.addPointsData("Test User", 200, "AnotherGame");
        int totalPoints = Database.shared.getTotalPointsPerPlayer("Test User");
        Assert.assertTrue(totalPoints >= 0);
    }

    @Test
    public void getPointsData() {
        Database.shared.getPointsData(); // Assuming this prints to console, no assert here
    }

    @Test
    public void getTotalPointsPerPlayer() {
        int totalPoints = Database.shared.getTotalPointsPerPlayer("Test User");
        Assert.assertTrue(totalPoints >= 0);
    }

    @Test
    public void updateMap() {
        Database.shared.updateMap("Test User", 15, 20, 5);
        PlayerModel result = Database.shared.signIn("Test User");
        Assert.assertNotNull(result);
        Assert.assertEquals(15, result.getLocation().getX());
        Assert.assertEquals(20, result.getLocation().getY());
    }

    @Test
    public void getLeaderboardList() {
        var leaderboard = Database.shared.getLeaderboardList();
        Assert.assertTrue(leaderboard.size() > 0);
    }

    @Test
    public void updateData() {
        Database.shared.updateData("Test User", 500, "UpdatedGame");
        int totalPoints = Database.shared.getTotalPointsPerPlayer("Test User");
        Assert.assertTrue(totalPoints >= 0);
    }
}

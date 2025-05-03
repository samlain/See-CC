import org.junit.*;
import java.util.List;
import java.util.Map;

public class LeaderboardModelTest {

    @Test
    public void testFetchLeaderboard() {
        // Make instance
        GameController gameController = new GameController();
        Database database = Database.shared;
        LeaderboardModel leaderboardModel = new LeaderboardModel(gameController, database);
        // Test the fetch function
        List<Map<String, Object>> leaderboard = leaderboardModel.fetchLeaderboard();
        Assert.assertNotNull(leaderboard);
        // Check if it's in right order
        for(int i= 0; i<leaderboard.size()-1;i++){
            int playerA = (int) leaderboard.get(i).get("total_points");
            int playerB = (int) leaderboard.get(i+1).get("total_points");
            Assert.assertFalse(playerA < playerB);
        }
        Assert.assertNotNull(leaderboard);
    }
}

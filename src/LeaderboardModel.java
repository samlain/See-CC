import java.util.*;
import java.util.Map;

/**
 * The LeaderboardModel class include the fetchLeaderboard method. Also get
 * the data from the database.
 */

public class LeaderboardModel {

    Database database;
    GameController controller;

    /**
     * Constructor
     * @param controller
     * @param databases
     */
    public LeaderboardModel(GameController controller, Database databases){
        this.controller = controller;
        this.database = databases;
    }

    /**
     * Fetch the leaderboard data from the database
     * This method get descending map data from the database. Each map is the information of a player.
     * @return Leaderboard as an ArrayList include the maps
     */
    public List<Map<String, Object>> fetchLeaderboard() {
        //fetch leaderboard from database
        // Here is an array of hashmap, map <username, total points>
        List<Map<String, Object>> leaderboard = database.getLeaderboardList();
        for (int i = 0; i < leaderboard.size(); i++) {
            Map<String, Object> getIndex = leaderboard.get(i);
            System.out.println("username: " + getIndex.get("username") + "  "+ "total Points: " + getIndex.get("total_points"));
        }
        return leaderboard;
    }
}



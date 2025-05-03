import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * This class is the controller of the leaderboard. For controlling the LeaderboardModel and LeaderboardView.
 * The method inside this class is to display the leaderboard panel with the fetching data.
 */

public class LeaderboardController {
    LeaderboardModel leaderboardModel;
    LeaderboardView leaderboardView;

    /**
     * Set up the LeaderboardModel and LeaderboardView
     * @param leaderboardModel
     * @param leaderboardView
     */

    public void set(LeaderboardModel leaderboardModel, LeaderboardView leaderboardView){
        this.leaderboardModel = leaderboardModel;
        this.leaderboardView = leaderboardView;
    }

    /**
     * Returns the JPanel with leaderboard on it and displaying the data after fetching
     * @return A JPanel display the username and points with the descending ranking.
     */
    public JPanel display() {
        //Return JPanel from leaderboard view
        if (leaderboardView == null || leaderboardModel == null){
            throw new RuntimeException();
        }
        // return the JPanel with fetched list
        List<Map<String, Object>> leaderboardList = leaderboardModel.fetchLeaderboard();
        return leaderboardView.display(leaderboardList);
    }

}


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * The leaderboard View class is made for using JTable to display the leaderboard.
 */


public class LeaderboardView {

    JTable leaderboardTable;

    /**
     * This method displays the leaderboard by using JTable.
     * It takes  a list as parameter from the database to keep the same type as the method in database.
     * @param leaderboardList The list of a map that each map contains information of each player
     * @return JPanel with two cols contains players' name and total points
     */

    public JPanel display(List<Map<String, Object>> leaderboardList) {
        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(new BorderLayout());

        String[] colName = {"Username", "Points"};
        // How many player then we have how many rows, two cols
        Object[][] data = new Object[leaderboardList.size()][2];
        // Use JTable to show

        for(int i=0; i< leaderboardList.size(); i++) {
            Map<String, Object> player = leaderboardList.get(i);
            // First col is players' name, the second col is players' total points
            data[i][0] = player.get("username");
            data[i][1] = player.get("total_points");
        }
        leaderboardTable = new JTable(data, colName);
        JScrollPane sp = new JScrollPane(leaderboardTable);
        leaderboard.add(sp, BorderLayout.CENTER);
        leaderboard.revalidate();
        leaderboard.repaint();
        return leaderboard;
    }


}


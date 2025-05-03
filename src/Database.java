import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The `Database` class manages connections and interactions with a MySQL database for player and game data.
 * It provides methods for signing up and signing in players, updating player locations, and managing player points.
 * The class also includes functionality for retrieving and updating data across different tables such as PLAYER, MAP, and POINTS.
 */
public class Database {

    /**
     * Singleton instance of the Database class.
     */
    static public Database shared = new Database();

    /**
     * Connection object used to interact with the MySQL database.
     */
    private Connection conn = null;

    /**
     * PreparedStatement object for executing SQL queries.
     */
    private PreparedStatement statement = null;

    /**
     * Constructs a Database instance and establishes a connection to the MySQL database.
     */
    public Database() {
        this.makeDBConnection();
    }

    /**
     * Establishes a connection to the MySQL database using JDBC.
     * Loads the MySQL JDBC driver and sets up the connection with auto-commit enabled.
     * Logs the connection status and handles any potential SQL or driver errors.
     */
    private void makeDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:1521/toes?user=o_moscow&password=Changeme_00");
            System.out.println("Database connection successful");
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a new player if the username does not already exist, or returns null if the player already exists.
     * Inserts player details into the PLAYER table and initializes map data.
     * @param username the username of the player
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @param icon the icon representing the player
     * @param major the major of the player
     * @return a PlayerModel object representing the new player, or null if the player already exists
     */
    public PlayerModel signUp(String username, String firstName, String lastName, String icon, String major) {
        String checkQuery = "SELECT uname FROM PLAYER WHERE uname = ?";
        String insertQuery = "INSERT INTO PLAYER (uname, fname, lname, icon, major) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                return null;
            } else {
                try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, username);
                    insertStatement.setString(2, firstName);
                    insertStatement.setString(3, lastName);
                    insertStatement.setString(4, icon);
                    insertStatement.setString(5, major);
                    insertStatement.executeUpdate();
                    this.addMapData(username, 0,0,0);
                    return new PlayerModel(username,firstName,lastName,icon,major,new Location(0,0), 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves player data by username from the PLAYER table and associated map data from the MAP table.
     * Returns a PlayerModel object containing the player's details and current location.
     * @param username the username of the player to sign in
     * @return a PlayerModel object with player details and location, or null if the player does not exist
     */
    public PlayerModel signIn(String username) {
        String selectQuery = "SELECT p.uname, p.fname, p.lname, p.icon, p.major, m.x, m.y FROM PLAYER p LEFT JOIN MAP m ON p.uname = m.uname WHERE p.uname = ?";

        try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String existingUsername = resultSet.getString("uname");
                String existingFirstName = resultSet.getString("fname");
                String existingLastName = resultSet.getString("lname");
                String existingIcon = resultSet.getString("icon");
                String existingMajor = resultSet.getString("major");
                int locationX = resultSet.getInt("x");
                int locationY = resultSet.getInt("y");
                int points = getTotalPointsPerPlayer(existingUsername);
                return new PlayerModel(existingUsername, existingFirstName, existingLastName, existingIcon, existingMajor, new Location(locationX, locationY), points);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the location of a player in the MAP table based on the provided coordinates.
     * Logs success or failure messages based on the update operation.
     * @param username the username of the player whose location is being updated
     * @param x the new x-coordinate of the player's location
     * @param y the new y-coordinate of the player's location
     */
    public void updatePlayerLocation(String username, int x, int y) {
        String updateQuery = "UPDATE MAP SET x = ?, y = ? WHERE uname = ?";

        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, x);
            updateStatement.setInt(2, y);
            updateStatement.setString(3, username);
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Player location updated successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and returns all player data from the PLAYER table.
     * Constructs an array of PlayerModel objects for each player.
     * @return an array of PlayerModel objects representing all players
     */
    public PlayerModel[] getPlayerData() {
        List<PlayerModel> players = new ArrayList<>();
        String query = "SELECT * FROM PLAYER";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet set = statement.executeQuery()) {

            while (set.next()) {
                String username = set.getString("uname");
                String icon = set.getString("icon");
                String major = set.getString("major");

                // Make a Player model for the new sign in player and add to list as finally we will return the players' list
                PlayerModel newPlayer = new PlayerModel(username, icon);
                players.add(newPlayer);
                players.add(new PlayerModel(username, icon));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players.toArray(new PlayerModel[0]);
    }

    /**
     * Retrieves and returns the player with the lowest amount robbed from the MAP table.
     * Constructs and returns a PlayerModel object with the player's details.
     * @return a PlayerModel object for the player with the lowest amount robbed, or null if no players are found
     */
    public PlayerModel getLowestRobbedPlayer() {
        String query = "SELECT m.uname, p.fname, p.lname, p.icon, p.major, m.x, m.y, m.amt_robbed FROM MAP m JOIN PLAYER p ON m.uname = p.uname ORDER BY m.amt_robbed ASC LIMIT 1;";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet set = statement.executeQuery()) {
            if (set.next()) {
                String username = set.getString("uname");
                String fname = set.getString("fname");
                String lname = set.getString("lname");
                String icon = set.getString("icon");
                String major = set.getString("major");
                Integer locx = set.getInt("x");
                Integer locy = set.getInt("y");
                Integer amtRobbed = set.getInt("amt_robbed");

                return new PlayerModel(username, fname, lname, icon, major, new Location(locx, locy), 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserts or updates map data for a player, including coordinates and amount robbed.
     * @param username the username of the player
     * @param x the x-coordinate for the player
     * @param y the y-coordinate for the player
     * @param amtRobbed the amount robbed by the player
     */
    public void addMapData(String username, int x, int y, int amtRobbed){
        String insertQuery = "INSERT INTO MAP (uname, x, y, amt_robbed) VALUES (?,?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(insertQuery)) {
            statement.setString(1, username);
            statement.setInt(2, x);
            statement.setInt(3, y);
            statement.setInt(4, amtRobbed);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and prints all map data from the MAP table.
     * Displays each entry's username, coordinates, and amount robbed.
     */
    public void getMapData() {
        String query = "SELECT * FROM MAP";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                String uname = set.getString("uname");
                int x = set.getInt("x");
                int y = set.getInt("y");
                int amtRobbed = set.getInt("amt_robbed");
                System.out.println("Username: " + uname + ", X: " + x + ", Y: " + y + ", Amount Robbed: " + amtRobbed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts points data into the POINTS table for a specific player and game.
     * @param username the username of the player
     * @param points the points to be added
     * @param game the game associated with the points
     */
    public void addPointsData(String username, int points, String game) {
        String query = "INSERT INTO POINTS (uname, points, game) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setInt(2, points);
            statement.setString(3, game);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and prints all points data from the POINTS table.
     * Displays each entry's username, points, and associated game.
     */
    public void getPointsData() {
        String query = "SELECT * FROM POINTS";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                String uname = set.getString("uname");
                int points = set.getInt("points");
                String game = set.getString("game");
                System.out.println("Username: " + uname + ", Points: " + points + ", Game: " + game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates and returns the total points accumulated by a specific player.
     * @param username the username of the player
     * @return the total points for the player
     */
    public int getTotalPointsPerPlayer(String username) {
        String query = "SELECT SUM(points) AS total_points FROM POINTS WHERE uname = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getInt("total_points");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Updates the map data for a player, including coordinates and accumulated amount robbed.
     * Fetches the current amount robbed and adds the new value to it.
     * @param username the username of the player
     * @param x the new x-coordinate for the player
     * @param y the new y-coordinate for the player
     * @param amtRobbed the additional amount robbed
     */
    public void updateMap(String username, int x, int y, int amtRobbed) {
        String selectQuery = "SELECT amt_robbed FROM MAP WHERE uname = ?";
        String updateQuery = "UPDATE MAP SET x = ?, y = ?, amt_robbed = ? WHERE uname = ?";

        try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int currentAmtRobbed = resultSet.getInt("amt_robbed");
                int newAmtRobbed = currentAmtRobbed + amtRobbed;

                try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                    updateStatement.setInt(1, x);
                    updateStatement.setInt(2, y);
                    updateStatement.setInt(3, newAmtRobbed);
                    updateStatement.setString(4, username);
                    updateStatement.executeUpdate();
                }
            } else {
                this.addMapData(username, x, y, amtRobbed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and returns a leaderboard list of players sorted by total points in descending order.
     * Constructs a list of maps, each containing a username and total points.
     * @return a list of maps representing the leaderboard, with each map containing a username and total points
     */
    public List<Map<String, Object>> getLeaderboardList() {
        List<Map<String, Object>> leaderboard = new ArrayList<>();
        String query = "SELECT uname, SUM(points) AS total_points FROM POINTS GROUP BY uname ORDER BY total_points DESC";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                Map<String, Object> playerData = new HashMap<>();
                playerData.put("username", set.getString("uname"));
                playerData.put("total_points", set.getInt("total_points"));
                leaderboard.add(playerData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    /**
     * Updates points data for a player in the POINTS table.
     * @param username the username of the player
     * @param points the new points value
     * @param game the game associated with the points
     */
    public void updateData(String username, int points, String game) {
        String updateQuery = "UPDATE POINTS SET points = ? WHERE uname = ? AND game = ?";
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, points);
            updateStatement.setString(2, username);
            updateStatement.setString(3, game);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

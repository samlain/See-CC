/**
 * This class is a player model. Will implement a player model for new user when they sign up.
 * Including the information of the players, as name, username, points, icon, location
 * Also share them to the database
 */

public class PlayerModel {

    static PlayerModel shared = null;
    private String name;
    private String username;
    private int points;
    private String icon;
    private Location myLoc;
    private String major;

    /**
     * This is the constructor
     * @param name
     * @param icon
     */

    public PlayerModel(String name, String icon) {
        this.name = name;
        this.username = name;
        // Initialize with 0;
        this.points = 0;
        this.icon = icon;
        this.myLoc = new Location(0, 0);
    }

    /**
     * This is the constructor
     * @param username
     * @param fname: first name
     * @param lname: last name
     * @param icon: icon show as character on map
     * @param major
     * @param location: (x, y)
     * @param points:
     */

    public PlayerModel(String username, String fname, String lname, String icon, String major, Location location, int points) {
        this.name = fname + " " + lname;
        this.username = username;
        this.icon = icon;
        this.myLoc = location;
        this.points = points;
        this.major = major;
    }

    /**
     * This method for moving. Need to enter a new location (int x, int y) as the parameter to let player move to
     * new location.
     * @param newLocation
     * @return myLoc, which is an instance of Player, the new location
     */
    public Location move(Location newLocation) {
        this.myLoc = newLocation;
        return myLoc;
    }

    /**
     * This method for getting the players' current points
     * @return current points
     */
    public int getPoints() {return this.points;}

    /**
     * This method is made for get person's name
     * @return full name
     */
    public String getName(){return name;}

    /**
     * This method is for getting users' username
     * @return username they made for themselves
     */
    public String getUsername() { return username; }

    /**
     * This method is for getting users' icon
     * @return icon name that will show up on the map
     */
    public String getIcon() {return icon;}

    /**
     * This method is for getting players' location (x, y)
     * @return (int x, int y)
     */
    public Location getLocation() {return myLoc;}

    /**
     * This method is for getting players' major
     * @return major
     */
    public String getMajor() {return major;}

    /**
     * This method is for calculating players' points
     * If the points is negative, set them as 0.
     * @param points
     * @param game
     */

    public void setPoints(int points, String game) {
        //check if player will have negative points
        if (this.points + points > 0) {
            this.points += points;
            Database.shared.addPointsData(this.getUsername(),points,game);
        } else {
            this.points = 0;
            Database.shared.addPointsData(this.getUsername(),-this.points,game);
        }
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }
}


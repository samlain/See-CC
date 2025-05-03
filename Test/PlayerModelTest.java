import org.junit.Assert;
import org.junit.Test;


public class PlayerModelTest {

    @Test
    public void testMove(){
        PlayerModel newPlayer = new PlayerModel("A", "a");
        Location newLocation = new Location(5,5);
        Assert.assertEquals(newLocation, newPlayer.move(newLocation));
    }

    @Test
    public void testInformation(){
        Location location = new Location(10, 5);
        PlayerModel newPlayer = new PlayerModel("uname", "fname", "lname", "icon", "major", location, 20);
        Assert.assertEquals("uname", newPlayer.getUsername());
        Assert.assertEquals("fname" + " " + "lname", newPlayer.getName());
        Assert.assertEquals("icon", newPlayer.getIcon());
        Assert.assertEquals("major", newPlayer.getMajor());
        Assert.assertEquals(location, newPlayer.getLocation());
        Assert.assertEquals(20, newPlayer.getPoints());
    }

    @Test
    public void testGetLocation(){
        PlayerModel newPlayer = new PlayerModel("A", "a");
        Location newLocaton = new Location(5, 5);
        newPlayer.move(newLocaton);
        Assert.assertEquals(newLocaton, newPlayer.getLocation());
    }

    @Test
    public void testGetPoints(){
        PlayerModel newPlayer = new PlayerModel("A", "a");
        Assert.assertEquals(0, newPlayer.getPoints());
    }

    @Test
    public void testGetName(){
        PlayerModel newPlayer = new PlayerModel("A", "a");
        Assert.assertEquals("A", newPlayer.getName());
    }

    @Test
    public void testGetIcon(){
        PlayerModel newPlayer = new PlayerModel("A", "a");
        Assert.assertEquals("a", newPlayer.getIcon());
    }

    @Test
    public void testGetMajor(){
        PlayerModel newPlayer = new PlayerModel("A", "a");
        Assert.assertEquals("A", newPlayer.getName());
    }

}
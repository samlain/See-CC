import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testX(){
        Location myLoc = new Location(5, 10);
        myLoc.setX(10);
        assertEquals(10, myLoc.getX());
    }
    @Test
    public void testY(){
        Location myLoc = new Location(5, 10);
        myLoc.setY(20);
        assertEquals(20, myLoc.getY());
    }
    @Test
    public void testConstructor(){
        Location myLoc = new Location(7, 3);
        assertEquals(7, myLoc.getX());
        assertEquals(3, myLoc.getY());
    }
}

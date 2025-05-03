import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest {
    @Test
    public void testConstructor(){
        Map<String, Number> myMap = new Map<String, Number>(10, 20);
        assertEquals(10, myMap.getWidth());
        assertEquals(20, myMap.getHeight());
    }

    @Test
    public void testRandomLocation(){
        Map<String, Number> myMap = new Map<String, Number>(10, 20);
        Location random = myMap.randomLocation();
        assertNotNull(random);
    }
    @Test
    public void testBounds(){
        Map<String, Number> myMap = new Map<String, Number>(10, 20);
        Location validLoc = new Location(3, 6);
        Location invalidLoc = new Location(-1, 21);
        assertTrue(myMap.isInBounds(validLoc));
        assertFalse(myMap.isInBounds(invalidLoc));
    }
}
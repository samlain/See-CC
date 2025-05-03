import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        DatabaseTest.class,
        LeaderboardModelTest.class,
        LocationTest.class,
        MapControllerTest.class,
        MapTest.class,
        PlayerModelTest.class,
        SlotModelTest.class,
        TriviaGameStrategyTest.class,
        TriviaModelTest.class,
        WeightGameStrategyTest.class,
        WeightModelTest.class
})

public class TestSuite extends TestCase {}


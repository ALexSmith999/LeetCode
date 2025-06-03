import graph.hard.LongestCycleGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LongestCycleGraphTests {
    LongestCycleGraph longest;
    @BeforeEach
    void setUp (){
        longest = new LongestCycleGraph();
    }
    @Test
    void longestCycleIsFound(){
        int[] edges = {3, 3, 4, 2, 3};
        assertEquals(3, longest.longestCycle(edges));
    }
    @Test
    void longestCycleIsNotFound(){
        int[] edges = {2, -1, 3, 1};
        assertEquals(-1, longest.longestCycle(edges));
    }
}

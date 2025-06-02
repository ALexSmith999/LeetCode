import graph.hard.ShortestCycleGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShortestCycleGraphTests {
    ShortestCycleGraph graph;
    @BeforeEach
    void setUp(){
        graph = new ShortestCycleGraph();
    }
    @Test
    void assertShortestCycleIsFound(){
        int n = 7;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 6}, {6, 3}};
        assertEquals(3, graph.findShortestCycle(n, edges));
    }
    @Test
    void assertShortestCycleIsNotFound(){
        int n = 4;
        int[][] edges = {{0, 1}, {0, 2}};
        assertEquals(-1, graph.findShortestCycle(n, edges));
    }
}

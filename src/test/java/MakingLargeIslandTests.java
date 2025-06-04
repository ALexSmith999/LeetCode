import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import union_find.hard.MakingLargeIsland;
import static org.junit.jupiter.api.Assertions.*;

public class MakingLargeIslandTests {
    MakingLargeIsland isl;
    @BeforeEach
    void setUp(){
        isl = new MakingLargeIsland();
    }
    @Test
    void makeLargestIslandTest(){
        int[][] grid = {{1, 1}, {1, 0}};
        int[][] grid1 = {{1, 0}, {0, 1}};
        int[][] grid2 = {{1, 1}, {1, 1}};
        assertEquals(4, isl.largestIsland(grid));
        assertEquals(3, isl.largestIsland(grid1));
        assertEquals(4, isl.largestIsland(grid2));
    }
}

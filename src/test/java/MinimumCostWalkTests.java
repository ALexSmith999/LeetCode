import graph.hard.MinimumCostWalk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumCostWalkTests {
    MinimumCostWalk cost;
    @BeforeEach
    void setUp(){
        cost = new MinimumCostWalk();
    }
    @Test
    void assertAlgorithmIsCorrect(){
        int n = 3;
        int [][] edges = {{0,2,7},{0,1,15},{1,2,6},{1,2,1}};
        int [][] query = {{1,2}};
        int res [] = cost.minimumCost(n, edges, query);
        int expected[] = new int[]{0};

        assertAll(
                () -> assertEquals(res.length, expected.length),
                () -> {
                    for (int i = 0; i < res.length; i++) {
                        assertEquals(expected[i], res[i]);
                    }
                }
        );
    }

    @Test
    void assertAlgorithmIsCorrect1(){
        int n1 = 5;
        int[][] edges1 = {{0, 1, 7}, {1, 3, 7}, {1, 2, 1}};
        int[][] query1 = {{0, 3}, {3, 4}};
        int res [] = cost.minimumCost(n1, edges1, query1);
        int expected[] = new int[]{1, -1};

        assertAll(
                () -> assertEquals(res.length, expected.length),
                () -> {
                    for (int i = 0; i < res.length; i++) {
                        assertEquals(expected[i], res[i]);
                    }
                }
        );
    }
}

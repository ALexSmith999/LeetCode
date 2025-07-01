import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import union_find.hard.SpanningTreeStability;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpanningTreeStabilityTests {
    SpanningTreeStability st ;
    @BeforeEach
    void setUp (){
        st = new SpanningTreeStability();
    }
    @Test
    void testStability1(){
        int n = 3;
        int[][] edges = {{0, 1, 2, 1}, {1, 2, 3, 0}};
        int k = 1;
        assertEquals(2, st.maxStability(n, edges, k), "Should be equals 2");
    }
    @Test
    void testStability2(){
        int n = 3;
        int[][] edges = {{0, 1, 4, 0}, {1, 2, 3, 0}, {0, 2, 1, 0}};
        int k = 2;
        assertEquals(6, st.maxStability(n, edges, k), "Should be equals 6");
    }
    @Test
    void testStability3(){
        int n = 3;
        int[][] edges = {{0, 1, 1, 1}, {1, 2, 1, 1}, {2, 0, 1, 1}};
        int k = 0;
        assertEquals(-1, st.maxStability(n, edges, k), "Should be equals -1");
    }
    @Test
    void testStability4(){
        int n = 5;
        int[][] edges = {
                {2, 4, 48733, 1},
                {3, 4, 858, 1},
                {0, 1, 96990, 0},
                {1, 4, 92483, 0},
                {0, 4, 78225, 0}};
        int k = 1;
        assertEquals(858, st.maxStability(n, edges, k), "Should be equals 858");
    }
    @Test
    void testStability5(){
        int n = 2;
        int[][] edges = {{0, 1, 87487, 0}};
        int k = 0;
        assertEquals(87487, st.maxStability(n, edges, k), "Should be equals 87487");
    }
    @Test
    void testStability6(){
        int n = 3;
        int[][] edges = {{0, 1, 55839, 0}, {0, 2, 39867, 0}, {1, 2, 62840, 0}};
        int k = 1;
        assertEquals(62840, st.maxStability(n, edges, k), "Should be equals 62840");
    }
}

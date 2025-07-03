import graph.hard.SecondTimeDestination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondTimeDestinationTests {
    SecondTimeDestination cl;
    @BeforeEach
    void setUp(){
        cl = new SecondTimeDestination();
    }
    @Test
    void assertWorksTest(){
        int n = 5, change = 5, time = 3;
        int [][] edges = {{1,2},{1,3},{1,4},{3,4},{4,5}};
        assertEquals(13, cl.secondMinimum(n, edges, time, change));
    }
    @Test
    void assertWorks1Test(){
        int n = 2, change = 2, time = 3;
        int [][] edges = {{1,2}};
        assertEquals(11, cl.secondMinimum(n, edges, time, change));
    }
}

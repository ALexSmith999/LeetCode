import graph.hard.ParallelCourses;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelCoursesTests {
    @Test
    void assertAlgorithmWorks(){
        ParallelCourses courses = new ParallelCourses();

        int n = 3;
        int [][] relations = {{1,3},{2,3}};
        int [] time = {3, 2, 5};
        assertEquals(8, courses.minimumTime(n, relations, time));

        int n1 = 5;
        int [][] relations1 = {{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}};
        int[] time1 = {1, 2, 3, 4, 5};
        assertEquals(12, courses.minimumTime(n1, relations1, time1));
    }
}

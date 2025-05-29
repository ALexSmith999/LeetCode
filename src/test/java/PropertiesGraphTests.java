import graph.medium.PropertiesGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesGraphTests {
    PropertiesGraph graph;

    @BeforeEach
    void SetUp() {
        graph = new PropertiesGraph();
    }

    @DisplayName("Checks the intersection of two arrays")
    @Test
    void isIntersectedTest(){
        int[] array1 = new int[]{1, 2, 3, 4};
        int[] array2 = new int[]{3, 4, 3, 4};
        int[] array3 = new int[]{1, 2, 5, 7};
        int[] array4 = new int[]{4, 4, 4, 4};

        assertAll(
                () -> assertTrue(graph.isIntersected(array1, array2, 2), "2 items intersect"),
                () -> assertFalse(graph.isIntersected(array1, array2, 3), "3 items intersect"),
                () -> assertTrue(graph.isIntersected(array1, array3, 2), "2 items intersect"),
                () -> assertTrue(graph.isIntersected(array1, array3, 1), "1 items intersect"),
                () -> assertFalse(graph.isIntersected(array1, array3, 3), "3 items intersect"),
                () -> assertTrue(graph.isIntersected(array2, array4, 1), "1 items intersect"),
                () -> assertFalse(graph.isIntersected(array2, array4, 2), "2 items intersect")
        );
    }

    @DisplayName("Checks the BFS algo")
    @Test
    void launchBFSTest (){
        //{0=[0, 1], 1=[0, 1], 2=[2, 3], 3=[2, 3, 4], 4=[3, 4], 5=[5]}
        Map<Integer, List<Integer>> mapa = Map.of(0, List.of(0, 1)
                , 1, List.of(0, 1)
                , 2, List.of(2, 3)
                , 3, List.of(2, 3, 4)
                , 4, List.of(3, 4)
                , 5, List.of(5)
        );
        graph.launchBFS(0, mapa);
        assertEquals(2, graph.getVisited().size());
        graph.launchBFS(2, mapa);
        assertEquals(5, graph.getVisited().size());
        graph.launchBFS(5, mapa);
        assertEquals(6, graph.getVisited().size());
    }
    @DisplayName("Test the primary method")
    @Nested
    class PropertiesGraphTests1 {
        @Test
        void shoulReturnThreeComponentsTest (){
            int[][] properties = {{1, 2}, {1, 1}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};
            int k = 1;
            assertEquals(3, graph.numberOfComponents(properties, k));
        }
        @Test
        void shoulReturnOneComponentsTest (){
            int[][] properties1 = {{1, 2, 3}, {2, 3, 4}, {4, 3, 5}};
            int k1 = 2;
            assertEquals(1, graph.numberOfComponents(properties1, k1));
        }
        @Test
        void shoulReturnTwoComponentsTest (){
            int[][] properties2 = {{1, 1}, {1, 1}};
            int k2 = 2;
            assertEquals(2, graph.numberOfComponents(properties2, k2));
        }
        @Test
        void shoulReturnOneAgainComponentsTest (){
            int[][] properties2 = {{1, 2, 3, 4, 5}, {7, 2, 3, 4, 8}, {9, 2, 3, 4, 11}};
            int k2 = 3;
            assertEquals(1, graph.numberOfComponents(properties2, k2));
        }
        @Test
        void shoulReturnThreeAgainComponentsTest (){
            int[][] properties2 = {{22, 21, 3, 4, 5}, {1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
            int k2 = 3;
            assertEquals(3, graph.numberOfComponents(properties2, k2));
        }
    }
}

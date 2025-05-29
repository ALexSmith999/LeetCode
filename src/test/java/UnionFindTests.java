import common_utils.UnionFind;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

public class UnionFindTests {
    UnionFind UFO = new UnionFind(10);

    @Test
    void validateunionFind (){
        assertAll(
                () -> {
                    for (int i = 0; i < UFO.getRanks().length; i++) {
                        assertEquals(1, UFO.getRanks()[i]);
                    }
                },
                () -> {
                    for (int i = 0; i < UFO.getVertexes().length; i++) {
                        assertEquals(i, UFO.getVertexes()[i]);
                    }
                },
                ()-> assertEquals(10, UFO.getComponentsNum())
                );
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class AlgorithmValidation {

        @Test
        @Order(1)
        void unionFirstandSecondIndexes () {
            UFO.union(0, 1);
            assertAll(
                    ()-> assertEquals(2, UFO.getRanks()[0]),
                    ()-> assertEquals(1, UFO.getRanks()[1]),
                    ()-> assertEquals(0, UFO.getVertexes()[0]),
                    ()-> assertEquals(0, UFO.getVertexes()[1], "Parent should change"),
                    ()-> assertTrue( UFO.areConnected(0, 1)),
                    ()-> assertEquals( 9, UFO.getComponentsNum())
            );
            UFO.union(0, 2);
            assertAll(
                    ()-> assertEquals(3, UFO.getRanks()[0], "Should add rank"),
                    ()-> assertEquals(1, UFO.getRanks()[2]),
                    ()-> assertEquals(0, UFO.getVertexes()[0]),
                    ()-> assertEquals(0, UFO.getVertexes()[2]),
                    ()-> assertTrue( UFO.areConnected(0, 2)),
                    ()-> assertTrue( UFO.areConnected(1, 2)),
                    ()-> assertEquals( 8, UFO.getComponentsNum())
            );
            UFO.union(1, 3);
            assertAll(
                    ()-> assertEquals(4, UFO.getRanks()[0], "Should add rank"),
                    ()-> assertEquals(1, UFO.getRanks()[1]),
                    ()-> assertEquals(0, UFO.getVertexes()[0]),
                    ()-> assertEquals(0, UFO.getVertexes()[3]),
                    ()-> assertEquals(0, UFO.getVertexes()[1]),
                    ()-> assertTrue( UFO.areConnected(1, 2)),
                    ()-> assertTrue( UFO.areConnected(0, 3)),
                    ()-> assertEquals( 7, UFO.getComponentsNum())
            );
        }
    }
}

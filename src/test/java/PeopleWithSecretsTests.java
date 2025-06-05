import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import union_find.hard.PeopleWithSecrets;
import java.util.List;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleWithSecretsTests {
    static PeopleWithSecrets inst;
    @BeforeAll
    static void setUp(){
        inst = new PeopleWithSecrets();
    }
    @Test
    void  assertAlgorithmWorks (){
        int n = 4;
        int [][] meetings = {{3,1,3},{1,2,2},{0,3,3}};
        int firstPerson = 3;
        assertEquals(List.of(0,1,3),
                inst.findAllPeople(n, meetings, firstPerson));

        int n1 = 5;
        int [][] meetings1 = {{3,4,2},{1,2,1},{2,3,1}};
        int firstPerson1 = 1;
        assertEquals(List.of(0,1,2,3,4),
                inst.findAllPeople(n1, meetings1, firstPerson1));

        int n2 = 6;
        int[][] meetings2 = {{1, 2, 5}, {2, 3, 8}, {1, 5, 10}};
        int firstPerson2 = 1;
        assertEquals(List.of(0,1,2,3,5),
                inst.findAllPeople(n2, meetings2, firstPerson2));
    }
}

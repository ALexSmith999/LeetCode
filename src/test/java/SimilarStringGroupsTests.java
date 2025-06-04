import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import union_find.hard.SimilarStringGroups;
import static org.junit.jupiter.api.Assertions.*;

public class SimilarStringGroupsTests {
    SimilarStringGroups gr;
    @BeforeEach
    void setUp(){
        gr = new SimilarStringGroups();
    }
    @Test
    void calcGroupsTest(){
        String [] strs = {"tars", "rats", "arts", "star"};
        assertEquals(2, gr.numSimilarGroups(strs));
    }
    @Test
    void calcGroups1Test(){
        String [] strs = {"omv","ovm"};
        assertEquals(1, gr.numSimilarGroups(strs));
    }
}

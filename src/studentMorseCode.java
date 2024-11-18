import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * @author DTekabe
 */
public class studentMorseCode {
    private MorseCodeTree morseCodeTree;

    @BeforeEach
    public void setUp() {
        morseCodeTree = new MorseCodeTree();
    }

    @Test
    public void testBuildTree() {
        // Verify the root of the tree
        assertNotNull(morseCodeTree.getRoot());
        assertEquals("", morseCodeTree.getRoot().getData());

        // Verify level 2
        assertEquals("e", morseCodeTree.getRoot().getLeftNode().getData());
        assertEquals("t", morseCodeTree.getRoot().getRightNode().getData());

        // Verify level 3
        assertEquals("i", morseCodeTree.getRoot().getLeftNode().getLeftNode().getData());
        assertEquals("a", morseCodeTree.getRoot().getLeftNode().getRightNode().getData());
        assertEquals("n", morseCodeTree.getRoot().getRightNode().getLeftNode().getData());
        assertEquals("m", morseCodeTree.getRoot().getRightNode().getRightNode().getData());
    }

    @Test
    public void testFetch() {
        // Test fetching letters from valid Morse code
        assertEquals("e", morseCodeTree.fetch("."));
        assertEquals("t", morseCodeTree.fetch("-"));
        assertEquals("i", morseCodeTree.fetch(".."));
        assertEquals("a", morseCodeTree.fetch(".-"));
        assertEquals("n", morseCodeTree.fetch("-."));
        assertEquals("m", morseCodeTree.fetch("--"));
        assertEquals("h", morseCodeTree.fetch("...."));
        assertEquals("z", morseCodeTree.fetch("--.."));

        
    }

    @Test
    public void testToArrayList() {
        // Get the in-order traversal list
        ArrayList<String> actualOrder = morseCodeTree.toArrayList();

    
        assertEquals(27, actualOrder.size()); //size including the empty one

        
        assertEquals("h", actualOrder.get(0));  
        assertEquals("e", actualOrder.get(6));  
        assertEquals("m", actualOrder.get(25)); 
    
    }

    @Test
    public void testInsert() {
        morseCodeTree.insert(".----", "test");
        assertEquals("test", morseCodeTree.fetch(".----"));
    }
}

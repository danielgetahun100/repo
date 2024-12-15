package assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	class TownTest {

	    private Town town1;
	    private Town town2;
	    private Town town3;

	    @BeforeEach
	    void setUp() {
	        town1 = new Town("TownA");
	        town2 = new Town("TownB");
	        town3 = new Town("TownA");
	    }

	    @Test
	    void testGetName() {
	        assertEquals("TownA", town1.getName());
	        assertEquals("TownB", town2.getName());
	    }

	    @Test
	    void testCompareTo() {
	        assertTrue(town1.compareTo(town2) < 0); 
	        assertEquals(0, town1.compareTo(town3)); 
	    }

	    @Test
	    void testToString() {
	        assertEquals("TownA", town1.toString());
	        assertEquals("TownB", town2.toString());
	    }

	    @Test
	    void testEquals() {
	        assertTrue(town1.equals(town3)); 
	        assertFalse(town1.equals(town2)); 
	        assertFalse(town1.equals("SomeString"));
	    }

	    @Test
	    void testHashCode() {
	        assertEquals(town1.hashCode(), town3.hashCode()); 
	    }

	    @Test
	    void testCopyConstructor() {
	        Town copiedTown = new Town(town1); 
	        assertEquals(town1.getName(), copiedTown.getName()); 
	        
	    }
	}}
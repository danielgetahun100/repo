package assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Road_STUDENT_Test {

    private Town townA;
    private Town townB;
    private Town townC;
    private Road road1;
    private Road road2;

    @BeforeEach
    public void setUp() {
        townA = new Town("Town A");
        townB = new Town("Town B");
        townC = new Town("Town C");

        road1 = new Road(townA, townB, 5, "Road 1");
        road2 = new Road(townA, townB, "Road 2"); //using the second constructor
    }

    @Test
    public void testConstructorWithWeight() {
        assertEquals("Road 1", road1.getName());
        assertEquals(5, road1.getWeight());
        assertEquals(townA, road1.getSource());
        assertEquals(townB, road1.getDestination());
    }

    @Test
    public void testConstructorWithoutWeight() {
        assertEquals("Road 2", road2.getName());
        assertEquals(1, road2.getWeight()); 
        assertEquals(townA, road2.getSource());
        assertEquals(townB, road2.getDestination());
    }

    @Test
    public void testSetWeight() {
        road1.setWeight(10);
        assertEquals(10, road1.getWeight());
    }

    @Test
    public void testSetSourceAndDestination() {
        road1.setSource(townC);
        road1.setDestination(townA);

        assertEquals(townC, road1.getSource());
        assertEquals(townA, road1.getDestination());
    }

    @Test
    public void testSetName() {
        road1.setNames("Name");
        assertEquals("Name", road1.getName());
    }

    @Test
    public void testContains() {
        assertTrue(road1.contains(townA));
        assertTrue(road1.contains(townB));
        assertFalse(road1.contains(townC));
    }

    @Test
    public void testToString() {
        assertEquals("Road 1", road1.toString());
        assertEquals("Road 2", road2.toString());
    }

    @Test
    public void testCompareTo() {
        Road road3 = new Road(townA, townB, "Road 3");
        assertTrue(road1.compareTo(road2) < 0); 
        assertTrue(road2.compareTo(road3) < 0); 
        assertTrue(road3.compareTo(road1) > 0); 
    }

    @Test
    public void testEquals() {
     
        Road differentRoad = new Road(townA, townC, 7, "Different Road");

        assertFalse(road1.equals(differentRoad)); 
        assertFalse(road1.equals(null)); 
        assertFalse(road1.equals(new Object())); 
    }
}

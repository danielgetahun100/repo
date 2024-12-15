package assignment6;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {
	private TownGraphManager manager;
	
	@BeforeEach
	void setUp() throws Exception {
		manager=new TownGraphManager();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	
	@Test
	void testAddTown() {
		assertTrue(manager.addTown("TownA"));
		assertTrue(manager.containsTown("TownA"));
		assertFalse(manager.addTown("TownA"));
		}
		
		@Test
	    void testAddRoad() {
	        manager.addTown("TownA");
	        manager.addTown("TownB");

	        assertTrue(manager.addRoad("TownA", "TownB", 5, "Road1"));
	        assertTrue(manager.containsRoadConnection("TownA", "TownB"));
	        assertEquals("Road1", manager.getRoad("TownA", "TownB"));
	    }

	    @Test
	    void testDeleteTown() {
	        manager.addTown("TownA");
	        manager.addTown("TownB");

	        manager.addRoad("TownA", "TownB", 5, "Road1");
	        assertTrue(manager.containsTown("TownA"));
	        assertTrue(manager.deleteTown("TownA"));
	        assertFalse(manager.containsTown("TownA"));
	        assertFalse(manager.containsRoadConnection("TownA", "TownB"));
	    }

	    @Test
	    void testAllTowns() {
	        manager.addTown("TownA");
	        manager.addTown("TownB");
	        manager.addTown("TownC");

	        assertEquals(3, manager.allTowns().size());
	        assertTrue(manager.allTowns().contains("TownA"));
	        assertTrue(manager.allTowns().contains("TownB"));
	        assertTrue(manager.allTowns().contains("TownC"));
	    }

	   
	}
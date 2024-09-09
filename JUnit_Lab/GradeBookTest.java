package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {
	private GradeBook g1;
	private GradeBook g2;

	@BeforeEach
	void setUp() throws Exception {
		
		g1=new GradeBook(5);
		g2=new GradeBook(5);
		
		g1.addScore(60);
		g1.addScore(10);
		
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		g1=null;
		g2=null;
	}
	@Test
	public void testAddScore() {
		assertTrue(g1.toString().equals("60.0 10.0 "));
		
		assertEquals(2,g1.getScoreSize());
		
		
	}
	@Test 
	public void testSum() {
		assertEquals(70, g1.sum(),0.0001);
		
	}
	@Test
	public void testMinimum() {
		assertEquals(10,g1.minimum(),0.001);
	}
	@Test
	public void testFinalScore() {
		assertEquals(60,g1.finalScore(),0.001);
		
	}

	

}

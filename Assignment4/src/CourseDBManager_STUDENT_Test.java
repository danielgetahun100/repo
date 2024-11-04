import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {

    private CourseDBManager courseDBManager;

    @Before
    public void setUp() throws Exception {
        courseDBManager = new CourseDBManager(); 
    }

    @After
    public void tearDown() throws Exception {
        courseDBManager = null; 
    }

    @Test
    public void testAddAndGet() {
        // Arrange
        String courseId = "CS101";
        int crn = 12345;
        int credits = 3;
        String roomNum = "Room 101";
        String instructor = "Dr. Smith";

        // Act
        courseDBManager.add(courseId, crn, credits, roomNum, instructor);
        CourseDBElement retrievedElement = courseDBManager.get(crn);

        // Assert
        assertNotNull("Retrieved CourseDBElement should not be null", retrievedElement);
        assertEquals( courseId, retrievedElement.getID());
        assertEquals( crn, retrievedElement.getCRN());
        assertEquals(credits, retrievedElement.getCredits());
        assertEquals(roomNum, retrievedElement.getRoomNum());
        assertEquals( instructor, retrievedElement.getInstructorName());
    } 
    @Test
    public void testShowAll() {
        // Arrange
        courseDBManager.add("CS101", 12345, 3, "Room 101", "Dr. Smith");
        courseDBManager.add("CS102", 12346, 4, "Room 102", "Dr. Jones");

        // Act
        ArrayList<String> allCourses = courseDBManager.showAll();

        // Assert
        assertEquals("There should be 2 courses in the database", 2, allCourses.size());
    }
}

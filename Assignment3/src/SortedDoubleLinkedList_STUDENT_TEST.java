import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedDoubleLinkedList_STUDENT_TEST {

    SortedDoubleLinkedList<String> sTest;
    SortedDoubleLinkedList<Boolean> bTest;
    SortedDoubleLinkedList<phones> phoneTest;

    phoneComparator comparatorphone;
    StringComparator comparatorS;

    public phones x = new phones("iphone x", 2020);
    public phones x1 = new phones("iphone 11", 2021);
    public phones x2 = new phones("iphone 12", 2022);
    public phones x3 = new phones("iphone 13", 2023);

    @BeforeEach
    void setUp() throws Exception {
        comparatorphone = new phoneComparator();
        comparatorS = new StringComparator();
        
        phoneTest = new SortedDoubleLinkedList<>(comparatorphone);
        sTest = new SortedDoubleLinkedList<>(comparatorS);

        // Add elements to the phoneTest
        phoneTest.add(x1);
        phoneTest.add(x2);

        // Add elements to the string list
        sTest.add("repeat");
        sTest.add("school");
        sTest.add("Eat");
        sTest.add("drink");
    }

    @AfterEach
    void tearDown() throws Exception {
        phoneTest = null;
        sTest = null;
        comparatorS = null;
        comparatorphone = null;
    }

    @Test
    public void testToArrayList() {
        ArrayList<phones> list;
        list = phoneTest.toArrayList();
        assertEquals(x1, list.get(0));
        assertEquals(x2, list.get(1));
    }

    @Test
    public void testIteratorSuccessfulNext() {
        ListIterator<String> iterator = sTest.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Eat", iterator.next());
        assertEquals("drink", iterator.next());
        assertEquals("repeat", iterator.next());
        assertEquals("school", iterator.next());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        ListIterator<String> iterator = sTest.iterator();

        iterator.next(); // Move to "Eat"
        iterator.next(); // Move to "drink"
        iterator.next(); // Move to "repeat"
        assertEquals("repeat", iterator.previous());
        assertEquals("drink", iterator.previous());
        assertEquals("Eat", iterator.previous());
    }

    @Test
    public void testRemove() {
     
    	phoneTest.add(x);
    	 assertEquals(x, phoneTest.getFirst());
    	 assertEquals(x2, phoneTest.getLast());
    	 
        
    	 phoneTest.remove(x2, comparatorphone);
    	 assertEquals(x1, phoneTest.getLast());
       
        phoneTest.remove(x3, comparatorphone);
        
    }
    @Test
    public void testRemovemiddle() {
     
    	phoneTest.add(x);
    	 assertEquals(x, phoneTest.getFirst());
    	 assertEquals(x2, phoneTest.getLast());
    	 
        
    	 phoneTest.remove(x1, comparatorphone);
    	 assertEquals(x2, phoneTest.getLast());
     
        
    }

    @Test
    public void retrieveFirstElement() {
     
        assertEquals(x1, phoneTest.retrieveFirstElement());

        assertEquals("Eat", sTest.retrieveFirstElement());
     
    }

    @Test
    public void retrieveLastElement() {
 
        assertEquals(x2, phoneTest.retrieveLastElement());
        assertEquals("school", sTest.retrieveLastElement());
  
    }

    private class phones {
        String name;
        int releaseDate;

        public phones() {
            name = null;
            releaseDate = 0;
        }

        public phones(String name, int date) {
            this.name = name;
            this.releaseDate = date;
        }

        public String getName() {
            return name;
        }

        public int releaseDate() {
            return releaseDate;
        }

        public String toString() {
            return (getName() + " " + releaseDate());
        }
    }

    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            return arg0.compareTo(arg1);
        }
    }

    private class phoneComparator implements Comparator<phones> {
        @Override
        public int compare(phones arg0, phones arg1) {
            return Integer.compare(arg0.releaseDate, arg1.releaseDate); // Compare by release date
        }
    }
}
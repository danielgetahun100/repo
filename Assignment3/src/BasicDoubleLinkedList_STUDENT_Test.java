import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class BasicDoubleLinkedList_STUDENT_Test {

	BasicDoubleLinkedList<String> sTest;
	BasicDoubleLinkedList<Boolean> bTest;
	BasicDoubleLinkedList<phones> phoneTest;


	phoneComparator comparatorphone;
	StringComparator comparatorS;
	DoubleComparator comparatorB;

	public phones x=new phones("iphone x", 2020);
	public phones x1=new phones("iphone 11", 2021);
	public phones x2=new phones("iphone 12", 2022);
	public phones x3=new phones("iphone 13", 2023);

	@BeforeEach
	void setUp() throws Exception {
		phoneTest=new BasicDoubleLinkedList<phones>();

		phoneTest.addToFront(x);
		phoneTest.addToEnd(x1);
		phoneTest.addToFront(x2);
		comparatorphone = new phoneComparator();

		bTest=new BasicDoubleLinkedList<Boolean>();
		bTest.addToEnd(Boolean.TRUE);
		bTest.addToEnd(Boolean.FALSE);
		comparatorB = new DoubleComparator();


		sTest= new BasicDoubleLinkedList<String>();
		sTest.addToEnd("Eat");
		sTest.addToEnd("drink");
		sTest.addToEnd("repeat");
		comparatorS= new StringComparator();



	}

	@AfterEach
	void tearDown() throws Exception {
		phoneTest=null;
		bTest=null;
		sTest=null;
		comparatorS=null;
		comparatorB=null;
		comparatorphone=null;
	}

	@Test
	public void testGetSize() {
		assertEquals(3,phoneTest.getSize());
		assertEquals(2,bTest.getSize());
		assertEquals(3,sTest.getSize());
	}

	@Test
	public void AddToEnd() {
		assertEquals("repeat",sTest.getLast());
		sTest.addToEnd("eat");
		assertEquals("eat",sTest.getLast());
		sTest.addToEnd("drink");
		assertEquals("drink",sTest.getLast());

		assertEquals(Boolean.FALSE,bTest.getLast());
	}
	@Test
	public void AddToFront() {
		assertEquals("Eat",sTest.getFirst());
		sTest.addToFront("Drink");
		assertEquals("Drink",sTest.getFirst());
		sTest.addToFront("Roll");
		assertEquals("Roll",sTest.getFirst());

		assertEquals(Boolean.FALSE,bTest.getLast());
	}

	@Test
	public void testGetFirst() {
		assertEquals(x2,phoneTest.getFirst());
		phoneTest.addToFront(x);
		assertEquals(x,phoneTest.getFirst());

	}

	@Test
	public void testGetLast() {
		assertEquals(x1,phoneTest.getLast());
		phoneTest.addToEnd(x);
		assertEquals(x,phoneTest.getLast());

	}
	@Test
	public void testToArraylist() {
		ArrayList<phones> list;
		list=phoneTest.toArrayList();
		assertEquals(x2,list.get(0));


	}

	@Test
	public void testIteratorSuccessfulNext() {
		sTest.addToFront("school");
		sTest.addToEnd("drive");
		ListIterator<String> iterator=sTest.iterator();

		assertEquals("school",sTest.getFirst());
		assertEquals(true,iterator.hasNext());
		assertEquals("school",iterator.next());
		assertEquals("Eat",iterator.next());
		assertEquals("drink",iterator.next());
		assertEquals("repeat",iterator.next());
		
		assertEquals("drive",iterator.next());
		


	}

	@Test
	public void testIteratorSuccessfulPrevious() {

		sTest.addToFront("school");
		sTest.addToEnd("drive");
		ListIterator<String> iterator=sTest.iterator();

		assertEquals("school",sTest.getFirst());
		assertEquals(true,iterator.hasNext());
		assertEquals("school",iterator.next());
		assertEquals("Eat",iterator.next());
		assertEquals("drink",iterator.next());
		
		assertEquals("drink",iterator.previous());
		assertEquals("Eat",iterator.previous());
	}
	
	@Test
	public void testRemove() {
		assertEquals(x2,phoneTest.getFirst());
		assertEquals(x1,phoneTest.getLast());
		phoneTest.addToEnd(x3);
		phoneTest.remove(x2, comparatorphone);
		assertEquals(x,phoneTest.getFirst());
		phoneTest.remove(x3, comparatorphone);
		assertEquals(x1,phoneTest.getLast());
		
	}
	@Test
	public void retrieveFirstElement() {
		assertEquals(x2,phoneTest.getFirst());
		assertEquals(x1,phoneTest.getLast());
		
		assertEquals(x2,phoneTest.retrieveFirstElement());
		assertEquals(x,phoneTest.retrieveFirstElement());
		
		assertEquals("Eat",sTest.getFirst());
		assertEquals("Eat",sTest.retrieveFirstElement());
	
		
	}
	@Test
	public void retrieveLastElement() {
		assertEquals(x2,phoneTest.getFirst());
		assertEquals(x1,phoneTest.getLast());
		
		assertEquals(x1,phoneTest.retrieveLastElement());
		assertEquals(x,phoneTest.retrieveLastElement());
		
		
		assertEquals("repeat",sTest.getLast());
		assertEquals("repeat",sTest.retrieveLastElement());
		//expected drink since repeat removed
		assertEquals("drink",sTest.getLast());
	
		
	}

	private class phones{
		String name;
		int releaseDate;

		public phones() {
			name=null;
			releaseDate=0;
		}
		public phones(String name , int date) {
			this.name=name;
			this.releaseDate=date;
		}

		public String getName(){
			return name;
		}
		public int releaseDate() {
			return releaseDate;
		}
		public String toString() {
			return (getName()+ " " + releaseDate());
		}

	}


	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}

	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}

	private class phoneComparator implements Comparator<phones>
	{

		@Override
		public int compare(phones arg0, phones arg1) {
			// Just put cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}

	}



}


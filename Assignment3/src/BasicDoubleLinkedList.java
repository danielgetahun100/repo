import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> extends Object implements Iterable<T> {

	protected Node<T> head;
	protected Node<T> tail;
	protected int size;

	public BasicDoubleLinkedList() {
		this.head=null;
		this.tail=null;
		this.size=0;
	}

	public int getSize() {
		return size;
	}

	public void addToEnd(T data) {
		Node<T> newNode=new Node<T>(data);

		if(tail==null) {
			head=tail=newNode;
		}
		else {
			tail.next=newNode;
			newNode.prev=tail;
			tail=newNode;
			
		}
		size++;
	}

	public void addToFront(T data) {

		Node<T> newNode =new Node<T>(data);
		if(head==null) {
			head=tail=newNode;
		}

		else {

			newNode.next=head;
			head.prev=newNode;
			head=newNode;
		}
		size++;
	}

	public T getFirst() {
		if(head==null) {
			return null;
		}
		else {
			return head.data;
		}
	}

	public T getLast() {
		if(tail==null){
			return null;
		}
		else {
			
			return tail.data;
		}
	}

	@Override
	public java.util.ListIterator<T> iterator() {

		return new DoubleLinkedListIterator();
	}



	public class Node<T>
	{
		Node<T> next;
		Node<T> prev;
		T data;

		Node(T dataNode){

			this.data=dataNode;
			this.next=null;
			this.prev=null;

		}
	
	}
	
	/**
	 * Iterator 
	 */
	public class DoubleLinkedListIterator implements ListIterator<T>{

		Node<T> current;
		Node<T> lastReturned;
		int position;


		public DoubleLinkedListIterator() {
			this.current=head;
			this.position=0;

		}

		public T next()
		{
			if(current==null) {
				throw new NoSuchElementException();
			}
			
			lastReturned=current;
			T data=current.data;
			
			current=current.next; 
		

			return data;

		}
		public boolean hasNext() {
			return current !=null ; 

		}
		
		public T previous()
		{
			
			if (!hasPrevious()) { // Check if there's a previous element
		        throw new NoSuchElementException(); // Throw exception if no previous element
		    }
			if (current != null) {
	            current = current.prev; // Move to the previous node
	        } else {
	            // If current is null, we want to start from lastReturned
	            current = lastReturned; // Start from last returned
	        }
		
			lastReturned = current; // Update lastReturned
	        return current.data;
			
		}
		
		public boolean hasPrevious() {

			return (current != null && current.prev != null) || (current == null && lastReturned != null);
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public void add(T arg0) {
			throw new UnsupportedOperationException();
		}
		
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		public void set(T arg0) {
			throw new UnsupportedOperationException();
		}

	}
	
	public Node<T> remove(T targetData,Comparator<T> comparator){
		
		Node<T> current=head;
		
		
		while(current!=null) {
			
			if(comparator.compare(current.data,targetData)==0) {
			  
				if(current.prev!=null) { //not head
					current.prev.next=current.next;// jump linkage over
				}
			else {// if current is head
					head=current.next;
				}
				
				if(current.next!=null){//not end 
					current.next.prev=current.prev;
				}
				else {
					tail=current.prev;
				}
				
				size--;
				return current;
				
		}
		
		current=current.next; //moving to next node
	}
		return null;
	
}
	/**
	 * 
	 * @return ArrayList<T>
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> display=new ArrayList<T>();
		Node<T> current =head;
		
		while(current!=null) {
			display.add(current.data);
			current=current.next;
		}
		
		return display;
 		
	}
	
	public T retrieveFirstElement() {
		if(head==null) {
			return null;
		}
		T temp=head.data;
		head=head.next;
		 if (head != null)  {
		        head.prev = null; 
		    } else
		        tail = null;      
		size--;
		return temp;
		
	}
	
	public T retrieveLastElement() {
		
		if(tail==null) {
			return null;
		}
		T data=tail.data;
		if(tail.prev==null)
			head=tail=null;
		else {
			tail=tail.prev;
			tail.next=null;
		}
		size--;
		return data;
	}


}




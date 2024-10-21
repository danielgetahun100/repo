import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private Comparator<T> comparator;

    // Constructor
    public SortedDoubleLinkedList(Comparator<T> compareableObject) {
        this.comparator = compareableObject;
    }

   
   
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        
        // If the list is empty, insert the node as the head and tail
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // Use the comparator to determine the insertion point
        if (comparator.compare(data, head.data) <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }

        Node<T> current = head;
        while (current != null) {
            if (comparator.compare(data, current.data) <= 0) {
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
                size++;
                return;
            }
            current = current.next;
        }

        // Insert at the tail if no earlier spot found
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
      
    }

    // This operation is not allowed for a sorted list
    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

   
    public ListIterator<T> iterator() {
        return super.iterator();
    }

   
   
	public Node remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator);
    }
}
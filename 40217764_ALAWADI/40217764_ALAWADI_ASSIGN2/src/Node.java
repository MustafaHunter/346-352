// Node class for my linkedlist
class Node<T> {
	// Using an expendable linkedlist to implement the stack, to avoid using Object[] and arraycopy. This will allow us to dynamically grow our stack without relying on array operations
    T data;
    
    Node<T> next;

    Node(T data) {
    	
        this.data = data;
        this.next = null;
    }
}

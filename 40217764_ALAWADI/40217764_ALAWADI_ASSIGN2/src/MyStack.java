class MyStack<T> {
	
    private Node<T> top;
    private int size;

    public MyStack() {
    	
        top = null;
        size = 0;
    }

    // Push element to the stack
    public void push(T element) {
    	
        Node<T> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop element off the stack
    
    public T pop() {
    	
        if (size == 0) {
        	
            throw new IllegalStateException("Stack is of size 0 and is empty");
        }
        
        T element = top.data;
        top = top.next;
        size--;
        return element;
    }

    // Peek at the item at the top of the stack
    public T peek() {
    	
        if (size == 0) {
            throw new IllegalStateException("Stack is of size 0 and is empty");
        }
        
        return top.data;
    }

    // Method to see if stack is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Method to get back the size of the stack
    public int size() {
        return size;
    }
}
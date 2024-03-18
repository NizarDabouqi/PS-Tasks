public class Stack<T> {
    private Object[] stack;
    private int capacity;
    private int size;

    // TODO, follow java naming convention, for constant variables use _ between words
    private final boolean IS_DYNAMIC;

    public Stack() {
        this(10, true);
    }

    public Stack(int capacity) {
        this(capacity, false);
    }

    private Stack(int capacity, boolean isDynamic) {
        this.capacity = capacity;
        this.IS_DYNAMIC = isDynamic;
        size = 0;
        stack = new Object[this.capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T newElement) {
        if (size == capacity) {
            if (IS_DYNAMIC) {
                capacity++;
                resizeStack();
            } else {
                throw new StackException("Stack overflow");
            }
        }
        stack[size++] = newElement;
    }

    public void pop() {
        if (isEmpty()) {
            throw new StackException("The stack is empty");
        }
        size--;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            throw new StackException("The stack is empty");
        }
        return (T) stack[size - 1];
    }

    private void resizeStack() {
        Object[] newArray = new Object[capacity];
        System.arraycopy(stack, 0, newArray, 0, size);
        stack = newArray;
    }
}

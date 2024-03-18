public class Queue<T> {
    private Object[] queue;
    private int capacity;
    private int size;
    private final boolean IS_DYNAMIC;


    public Queue() {
        this(10, true);
    }

    public Queue(int capacity) {
        this(capacity, false);
    }

    private Queue(int capacity, boolean isDynamic) {
        this.capacity = capacity;
        this.IS_DYNAMIC = isDynamic;
        size = 0;
        queue = new Object[this.capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enque(T newElement) {
        if (size == capacity) {
            if (IS_DYNAMIC) {
                capacity++;
                resizeQueue();
            } else {
                throw new QueueException("Queue overflow");
            }
        }
        queue[size++] = newElement;
    }

    public void deque() {
        if (isEmpty()) {
            throw new QueueException("The queue is empty");
        }
        System.arraycopy(queue, 1, queue, 0, size - 1);
        size--;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            throw new QueueException("The queue is empty");
        }
        return (T) queue[0];
    }

    private void resizeQueue() {
        Object[] newArray = new Object[capacity];
        System.arraycopy(queue, 0, newArray, 0, size);
        queue = newArray;
    }
}

public class Main {
    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>(10);

        System.out.println("Size = "+ queue.size());

        for (int i = 0; i < 11; i++) {
            queue.enque(i + 1);
        }
        System.out.println("Size = "+ queue.size());


        queue.deque();
        queue.deque();
        queue.deque();
        System.out.println(queue.peek());
        queue.deque();
        queue.deque();
        queue.deque();
        queue.deque();


        System.out.println(queue.peek());
        System.out.println("Size = "+ queue.size());

    }
}







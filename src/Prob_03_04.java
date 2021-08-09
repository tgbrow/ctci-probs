import java.util.Stack;

public class Prob_03_04 {

    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>();
        System.out.println("   add: 1, 2, 3");
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println("  size: " + q.size());   // expect 3
        System.out.println("  peek: " + q.peek());   // expect 1
        System.out.println("remove: " + q.remove()); // expect 1
        System.out.println("   add: 4, 5");
        q.add(4);
        q.add(5);
        System.out.println("  size: " + q.size());   // expect 4
        System.out.println("  peek: " + q.peek());   // expect 2
        System.out.println("remove: " + q.remove()); // expect 2
        System.out.println("   add: 6");
        q.add(6);
        System.out.println("  size: " + q.size());   // expect 4
        System.out.println("  peek: " + q.peek());   // expect 3
        System.out.println("remove: " + q.remove()); // expect 3
        System.out.println("remove: " + q.remove()); // expect 4
        System.out.println("remove: " + q.remove()); // expect 5
        System.out.println("  size: " + q.size());   // expect 1
        System.out.println("remove: " + q.remove()); // expect 6
        System.out.println("  peek: " + q.peek());   // expect null
    }
    
    public static class MyQueue<T> {
        Stack<T> fifo, filo;

        public MyQueue() {
            fifo = new Stack<T>();
            filo = new Stack<T>();
        }

        public void add(T value) {
            fifo.push(value);
        }

        public T remove() {
            cycleStacksIfNeeded();
            // TODO: catch EmptyStackException, rethrow as NoSuchElementException
            return filo.pop();
        }

        public T peek() {
            cycleStacksIfNeeded();
            // Match behavior of Java's Queue.peek()
            if (filo.isEmpty()) return null;
            return filo.peek();
        } 

        public int size() {
            return fifo.size() + filo.size();
        }

        private void cycleStacksIfNeeded() {
            if (filo.isEmpty()) {
                while (!fifo.isEmpty()) {
                    filo.push(fifo.pop());
                }
            }
        }
    }
}

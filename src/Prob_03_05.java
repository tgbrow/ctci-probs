import java.util.Stack;

public class Prob_03_05 {
    
    public static void main(String[] args) {
        Stack<Integer> ints = new Stack<>();
        ints.add(5);
        ints.add(2);
        ints.add(3);
        ints.add(1);
        ints.add(4);
        System.out.println("before: top [ " + stackString(ints) + "] bot");
        sort(ints);
        System.out.println(" after: top [ " + stackString(ints) + "] bot");

        Stack<String> strs = new Stack<>();
        strs.add("cat");
        strs.add("zoo");
        strs.add("tip");
        strs.add("cut");
        strs.add("joe");
        strs.add("zoo");
        strs.add("abc");
        System.out.println("before: top [ " + stackString(strs) + "] bot");
        sort(strs);
        System.out.println(" after: top [ " + stackString(strs) + "] bot");
    }

    public static <T> String stackString(Stack<T> s) {
        Stack<T> aux = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (!s.empty()) {
            T temp = s.pop();
            sb.append(temp);
            sb.append(" ");
            aux.push(temp);
        }
        while (!aux.empty()) {
            s.push(aux.pop());
        }
        return sb.toString();
    }

    private static <T extends Comparable<T>> void sort(Stack<T> s) {
        if (s.size() < 2) return;

        // Move everything into auxiliary stack.
        Stack<T> aux = new Stack<>();
        while (s.size() > 1) {
            aux.push(s.pop());
        }

        while (!aux.empty()) {
            T temp = aux.pop();
            while (!s.empty() && s.peek().compareTo(temp) < 0) {
                aux.push(s.pop());
            }
            s.push(temp);
        }
    }
}

import java.util.Stack;

public class Prob_02_06 {

    public static void main(String[] args) {
        int[][] testData =  {
            {1, 2, 3, 2, 1},
            {1, 2, 3, 3, 2, 1},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5, 6},
            {},
            {1},
            {1, 1},
            {1, 1, 1},
        };
        for (int[] data : testData) {
            LinkedListNode n = LinkedListNode.buildSinglyLinkedList(data);
            System.out.printf("%s : %s\n", n, isPalindrome(n));
        }
    }
    
    private static boolean isPalindrome(LinkedListNode head) {
        if (head == null) return true;

        Stack<Integer> s = new Stack<Integer>();
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        while (fast != null) {
            if (fast.next == null) {
                slow = slow.next;
                break;
            }
            s.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        while (slow != null) {
            if (slow.data != s.pop()) return false;
            slow = slow.next;
        }

        return true;
    }

}

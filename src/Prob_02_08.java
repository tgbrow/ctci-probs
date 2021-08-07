public class Prob_02_08 {

    public static void main(String[] args) {
        int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i <= 9; ++i) {
            LinkedListNode test = LinkedListNode.buildSinglyLinkedList(data);
            loopTailToNode(test, i);
            System.out.printf("%d: loop start @ %d\n", i, findLoopStart(test).data);
        }
    }

    // Method to facilitate test setup.
    // Warning: doesn't include proper validation. 
    private static void loopTailToNode(LinkedListNode head, int index) {
        LinkedListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        LinkedListNode indexNode = head;
        for (int i = 0; i < index; ++i) {
            indexNode = indexNode.next;
        }
        tail.next = indexNode;
    }

    private static LinkedListNode findLoopStart(LinkedListNode head) {
        if (head == null) return null;

        // Progress slow by 1 and fast by 2 until they collide (assuming loop exists).
        // If a loop exists, slow and fast are guaranteed to eventually collide...
        // because math :)
        LinkedListNode slow = head, fast = head;
        do {
            if (fast.next == null || fast.next.next == null) {
                // List doesn't contain a loop.
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        // At this point, we know the distance between fast and the start of the loop is
        // the same as the distance between head and the start of the loop (again, because
        // math). So, if we start at head and iterate in lockstep with fast, the two will
        // collide at the start of the loop.
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
    
}

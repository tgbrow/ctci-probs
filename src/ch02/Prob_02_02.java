package ch02;

import common.LinkedListNode;

public class Prob_02_02 {
    
    public static void main(String[] args) {
        int[] testData = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        LinkedListNode list = LinkedListNode.buildSinglyLinkedList(testData);
        for (int i = -1; i < 12; ++i) {
            LinkedListNode resultRec  = kthToLastRec(list, i);
            LinkedListNode resultIter = kthToLastIter(list, i);
            System.out.printf("k = %d: %s / %s\n",
                i,
                resultRec  == null ? "null" :  resultRec.data,
                resultIter == null ? "null" : resultIter.data);
        }
    }

    // Iterative Solution
    private static LinkedListNode kthToLastIter(LinkedListNode head, int k)  {
        if (k < 1) return null;

        LinkedListNode n1 = head;
        LinkedListNode n2 = head;
        
        // offset n1 and n2 by k-1
        for (int i = 0; i < k-1; ++i) {
            if (n2.next == null) return null;
            n2 = n2.next;
        }

        // keep shift n1 and n2 forward until n2 is the last element
        while (n2.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1;
    }

    // Recursive Solution
    private static LinkedListNode kthToLastRec(LinkedListNode head, int k)  {
        if (k < 1) return null;
        IntWrap fromLast = new IntWrap(-1);
        LinkedListNode result = kthToLastRecHelper(head, k, fromLast);
        return fromLast.val == k ? result : null;
    }

    private static LinkedListNode kthToLastRecHelper(LinkedListNode n, int k, IntWrap fromLast)  {
        if (n.next == null) {
            fromLast.val = 1;
            return n;
        }
        
        LinkedListNode node = kthToLastRecHelper(n.next, k, fromLast);
        if (fromLast.val == k) {
            return node;
        } 

        fromLast.val++;
        return n;
    }

    private static class IntWrap {
        int val = 0;
        public IntWrap(int i) {
            this.val = i;
        }
    }
}

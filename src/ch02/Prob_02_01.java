package ch02;

import common.LinkedListNode;
import java.util.HashSet;
import java.util.Set;

public class Prob_02_01 {

    // Note: Assume we're dealing with a *singly* linked list.

    public static void main(String[] args) {
        int[] testData = {1, 2, 3, 1, 5, 6, 1, 1, 9, 10, 5};
        LinkedListNode list1 = LinkedListNode.buildSinglyLinkedList(testData);
        LinkedListNode list2 = list1.clone();
        System.out.println(list1);
        removeDuplicates(list1);
        System.out.println(list1);
        removeDuplicatesBeta(list2);
        System.out.println(list2);
    }

    private static void removeDuplicates(LinkedListNode n) {
        Set<Integer> seen = new HashSet<>();
        LinkedListNode prev = null;

        while (n != null) {
            if (!seen.add(n.data)) {
                // Note: It's impossible for prev to be null for a duplicate.
                prev.next = n.next;
            } else { // n is not a duplicate
                prev = n;
            }
            n = n.next;
        }
    }

    // Version if we can't use aux data structure.
    private static void removeDuplicatesBeta(LinkedListNode n) {
        while (n != null) {
            LinkedListNode runner = n;
            while (runner.next != null) {
                if (runner.next.data == n.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            n = n.next;
        }
    }
    

}

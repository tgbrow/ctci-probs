package ch02;

import common.LinkedListNode;
import java.util.HashSet;
import java.util.Set;

public class Prob_02_07 {

    public static void main(String[] args) {
        int[] aData = {0, 1, 2, 3};
        int[] bData = {4, 5, 6};
        int[] cData = {7, 8, 9};
        LinkedListNode a = LinkedListNode.buildSinglyLinkedList(aData);
        LinkedListNode b = LinkedListNode.buildSinglyLinkedList(bData);
        LinkedListNode c = LinkedListNode.buildSinglyLinkedList(cData);
        a.appendSingly(c);
        b.appendSingly(c);

        LinkedListNode inter = intersection(a, b);
        LinkedListNode interNA = intersectionNoAux(a, b);
        System.out.printf("intersection @ %s / %s\n", 
            (inter   != null ? inter.data   : "null"),
            (interNA != null ? interNA.data : "null"));
    }

    // Version using aux data structure.
    private static LinkedListNode intersection(LinkedListNode a, LinkedListNode b) {
        if (a == null || b == null) return null;

        Set<LinkedListNode> aNodes = new HashSet<>();
        while (a != null) {
            aNodes.add(a);
            a = a.next;
        }

        while (b != null) {
            if (aNodes.contains(b)) return b;
            b = b.next;
        }
        return null;
    }

    // Version not using aux data structure.
    private static LinkedListNode intersectionNoAux(LinkedListNode a, LinkedListNode b) {
        if (a == null || b == null) return null;

        TailAndLength tlA = findTailAndLength(a);
        TailAndLength tlB = findTailAndLength(b);

        if (tlA.tail != tlB.tail) return null;

        LinkedListNode shorter = a, longer = b;
        if (tlA.length > tlB.length) {
            shorter = b;
            longer  = a;
        } 

        int lenDiff = Math.abs(tlA.length - tlB.length);
        for ( ; lenDiff > 0; --lenDiff) {
            longer = longer.next;
        }

        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }
        return shorter;
    }

    private static TailAndLength findTailAndLength(LinkedListNode n) {
        if (n == null) return new TailAndLength(null, 0);

        TailAndLength result = new TailAndLength(n, 1);
        while (result.tail.next != null) {
            result.length++;
            result.tail = result.tail.next;
        }
        return result;
    }

    private static class TailAndLength {
        LinkedListNode tail;
        int length;
        public TailAndLength(LinkedListNode tail, int size) {
            this.tail = tail;
            this.length = size;
        }
    }
    
}

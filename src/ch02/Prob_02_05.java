package ch02;

import common.LinkedListNode;

public class Prob_02_05 {

    public static void main(String[] args) {
        int[][][] testData = {
            { {1, 2, 3}, {3, 2, 1} },
            { {8, 7, 8}, {3, 1, 1} },
            { {1, 2, 3}, {4, 5, 6, 7} },
            { {}, {} },
            { {0}, {0} },
            { {0}, {3, 2, 1} },
            { {9, 9, 9, 9}, {1} },
            { {9, 9, 9, 9}, {9, 9, 8} },
        };
        for (int[][] td : testData) {
            LinkedListNode a = LinkedListNode.buildSinglyLinkedList(td[0]);
            LinkedListNode b = LinkedListNode.buildSinglyLinkedList(td[1]);
            System.out.printf("a: %s\n", a);
            System.out.printf("b: %s\n", b);
            System.out.println("sum reverse iterative");
            System.out.printf("   %s\n", sumBackwardIterative(a, b));
            System.out.println("sum reverse recursive");
            System.out.printf("   %s\n", sumBackwardRecursive(a, b));
            System.out.println("sum forward iterative");
            System.out.printf("   %s\n", sumForwardIterative(a, b));
            System.out.println("sum forward recursive");
            System.out.printf("   %s\n", sumForwardRecursive(a, b));
            System.out.println();
        }
    }
    
    // Assumption: All digits in lists a and b are non-negative.
    // (In a full solution, I would add validation to assert this assumption.)

    // Iterative solution for digits in reverse order (e.g. "3 > 2 > 1" means "123").
    private static LinkedListNode sumBackwardIterative(LinkedListNode a, LinkedListNode b) {
        if (a == null && b == null) {
            return new LinkedListNode(0);
        } else if (a == null) {
            return b.clone();
        } else if (b == null) {
            return a.clone();
        }
        // If we reach this point, we know a and b each have at least one digit.

        LinkedListNode sumHead = null;
        LinkedListNode sumTail = null;
        boolean carry = false;

        while (a != null || b != null) {
            int digitSum = (a != null ? a.data : 0) + (b != null ? b.data : 0) + (carry ? 1 : 0);
            carry = digitSum >= 10;
            digitSum %= 10;
            LinkedListNode newDigit = new LinkedListNode(digitSum);
            if (sumHead == null) {
                sumHead = newDigit;
            } else {
                sumTail.next = newDigit;
            }
            sumTail = newDigit;
            if (a != null) a = a.next;
            if (b != null) b = b.next;
        }

        if (carry) {
            // Note: We know sumTail != null here because a and b must have
            // each had at least one digit if we've reached this point.
            sumTail.next = new LinkedListNode(1);
        }

        return sumHead;
    }    

    // Recursive solution for digits in reverse order (e.g. "3 > 2 > 1" means "123").
    private static LinkedListNode sumBackwardRecursive(LinkedListNode a, LinkedListNode b) {
        LinkedListNode result = sbrHelper(a, b, false);
        return result != null ? result : new LinkedListNode(0);
    }

    private static LinkedListNode sbrHelper(LinkedListNode a, LinkedListNode b, boolean carry) {
        if (a == null && b == null && !carry) return null;

        int digitSum = (a != null ? a.data : 0) + (b != null ? b.data : 0) + (carry ? 1 : 0);
        carry = digitSum >= 10;

        LinkedListNode digit = new LinkedListNode(digitSum % 10);
        digit.next = sbrHelper((a != null ? a.next : null), (b != null ? b.next : null), carry);
        return digit;
    }

    // Iterative solution for digits in standard order (e.g. "3 > 2 > 1" means "321").
    private static LinkedListNode sumForwardIterative(LinkedListNode a, LinkedListNode b) {
        int aNum = getNum(a);
        int bNum = getNum(b);
        int sumNum = aNum + bNum;
        if (sumNum == 0) return new LinkedListNode(0);

        LinkedListNode sumHead = null;
        LinkedListNode sumTail = null;
        int place = (int) Math.log10(sumNum);

        for ( ; place >= 0; --place) {
            LinkedListNode digit = new LinkedListNode((sumNum / (int) Math.pow(10, place)) % 10);
            if (sumHead == null) {
                sumHead = digit;
            } else {
                sumTail.next = digit;
            }
            sumTail = digit;
        }

        return sumHead;
    }

    private static int getNum(LinkedListNode n) {
        int num = 0;
        while (n != null) {
            num = 10 * num + n.data;
            n = n.next;
        }
        return num;
    }

    // Recursive solution for digits in standard order (e.g. "3 > 2 > 1" means "321").
    private static LinkedListNode sumForwardRecursive(LinkedListNode a, LinkedListNode b) {
        if (a == null && b == null) {
            return new LinkedListNode(0);
        }

        // Make lists a and b the same length.
        int lenA = LinkedListNode.getLength(a);
        int lenB = LinkedListNode.getLength(b);
        if (lenA < lenB) {
            a = padZeros(a, lenB-lenA);
        } else {
            b = padZeros(b, lenA-lenB);
        }

        NodeAndCarry nac = sfrHelper(a, b);
        LinkedListNode result = nac.node;
        if (nac.carry) {
            LinkedListNode temp = new LinkedListNode(1);
            temp.next = result;
            result = temp;
        }
        return result;
    }

    // pre: a and b are the same length
    // In a full implementation, I would add validation of the pre-condition.
    private static NodeAndCarry sfrHelper(LinkedListNode a, LinkedListNode b) {
        NodeAndCarry next = new NodeAndCarry();
        if (a.next != null) {
            // Here, we know b.next != null since a and b are the same length.
            next = sfrHelper(a.next, b.next);
        }

        int digitSum = (a != null ? a.data : 0) + (b != null ? b.data : 0) + (next.carry ? 1 : 0);
        NodeAndCarry current = new NodeAndCarry();
        current.carry = digitSum >= 10;

        current.node = new LinkedListNode(digitSum % 10);
        current.node.next = next.node;
        return current;
    }

    private static LinkedListNode padZeros(LinkedListNode head, int zeros) {
        for ( ; zeros > 0; --zeros) {
            LinkedListNode z = new LinkedListNode(0);
            z.next = head;
            head = z;
        }
        return head;
    }

    private static class NodeAndCarry {
        LinkedListNode node;
        boolean carry;
    }
}

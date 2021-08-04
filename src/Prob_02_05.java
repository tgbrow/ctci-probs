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
        };
        for (int i = 0; i < testData.length; ++i) {
            LinkedListNode a = LinkedListNode.buildSinglyLinkedList(testData[i][0]);
            LinkedListNode b = LinkedListNode.buildSinglyLinkedList(testData[i][1]);
            System.out.printf("a: %s\n", a);
            System.out.printf("b: %s\n", b);
            System.out.println("sum reverse iterative");
            System.out.printf("   %s\n", sumBackwardIterative(a, b));
            System.out.println("sum reverse recursive");
            System.out.printf("   %s\n", sumBackwardRecursive(a, b));
            System.out.println("sum forward iterative");
            System.out.printf("   %s\n", sumForwardIterative(a, b));
            System.out.println();
        }
    }
    
    // Assumption: All digits in lists a and b are non-negative.

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
        LinkedListNode result = sBRHelper(a, b, false);
        return result != null ? result : new LinkedListNode(0);
    }

    private static LinkedListNode sBRHelper(LinkedListNode a, LinkedListNode b, boolean carry) {
        if (a == null && b == null && !carry) return null;

        int digitSum = (a != null ? a.data : 0) + (b != null ? b.data : 0) + (carry ? 1 : 0);
        carry = digitSum >= 10;
        digitSum %= 10;

        LinkedListNode digit = new LinkedListNode(digitSum);
        digit.next = sBRHelper((a != null ? a.next : null), (b != null ? b.next : null), carry);
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
        // TODO:  implement this
        return null;
    }
}

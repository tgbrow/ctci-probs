public class Prob_02_04 {

    public static void main(String[] args) {
        int[] testData = {2, 3, 7, 9, 1, 4, 8, 9, 5, 2, 9, 1, 3};
        LinkedListNode list = LinkedListNode.buildSinglyLinkedList(testData);
        System.out.println(list);
        list = partition(list, 5);
        System.out.println(list);
    }

    private static LinkedListNode partition(LinkedListNode head, int x) {
        if (head == null) return null;

        LinkedListNode runner = head;
        boolean pastPartition = false;

        while (runner.next != null) {
            if (runner.data >= x) {
                pastPartition = true;
            }
            if (pastPartition && runner.next.data < x) {
                LinkedListNode temp = runner.next.next;
                runner.next.next = head;
                head = runner.next;
                runner.next = temp;
            } else {
                runner = runner.next;
            }
        }

        return head;
    }
    
}

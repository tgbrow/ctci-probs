public class Prob_02_03 {

    public static void main(String[] args) {
        int[] testData = {1, 2, 3, 4, 5, 6, 7};
        LinkedListNode list = LinkedListNode.buildSinglyLinkedList(testData);
        System.out.println(list);
        goofyDelete(list.next.next);
        System.out.println(list);
    }

    // Problem lets us assume the input node is not first or last in list.
    private static void goofyDelete(LinkedListNode n) {
        n.data = n.next.data;
        n.next = n.next.next;
    }
    
}

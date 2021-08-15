package common;

// Provided in book's "Code Library"
public class LinkedListNode {
    public LinkedListNode next, prev, last;
    public int data;

    public LinkedListNode(int d, LinkedListNode n, LinkedListNode p) {
        data = d;
        setNext(n);
        setPrevious(p);
    }
    
    public LinkedListNode(int d) {
        data = d;
    }

    public LinkedListNode() {}

    public void setNext(LinkedListNode n) {
        next = n;
        if (this == last) {
            last = n;
        }
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }

    public void setPrevious(LinkedListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }

    public LinkedListNode clone() {
        LinkedListNode next2 = null;
        if (next != null) {
            next2 = next.clone();
        }
        LinkedListNode head2 = new LinkedListNode(data, next2, null);
        return head2;
    }

    // Note: The methods below are my additions (not from book).
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedListNode n = this;
        while (n != null) {
            sb.append(n.data);
            if (n.next != null) {
                sb.append(" > ");
            }
            n = n.next;
        }
        return sb.toString(); 
    }

    public void appendSingly(LinkedListNode other) {
        LinkedListNode tail = this;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = other;
    }

    public static int getLength(LinkedListNode n) {
        int len = 0;
        while (n != null) {
            ++len;
            n = n.next;
        }
        return len;
    }

    public static LinkedListNode buildSinglyLinkedList(int[] data) {
        if (data.length < 1) return null;
        LinkedListNode head = new LinkedListNode(data[0]);
        LinkedListNode n = head;
        for (int i = 1; i < data.length; ++i) {
            n.next = new LinkedListNode(data[i]);
            n = n.next;
        }
        return head;
    }
}

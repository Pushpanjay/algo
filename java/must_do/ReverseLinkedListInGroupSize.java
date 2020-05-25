package amzn.must_do;

import misc.SingleLinkedList;
import misc.SingleLinkedListNode;

/**
 * @author pushpanjay.kumar created on 22/3/20
 */
// Reverse a Linked List in groups of given size.
public class ReverseLinkedListInGroupSize {

    static SingleLinkedListNode reverseInGroupSize(SingleLinkedListNode head, int k){
        int count=0;

        SingleLinkedListNode prev = null;
        SingleLinkedListNode next = null;
        SingleLinkedListNode current = head;

        while(count<k && current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        if(next!=null){
            head.next = reverseInGroupSize(next, k);
        }
        return prev;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        list.insertAtEnd(4);
        list.insertInBeg(0);
        list.insertAtEnd(5);
        list.insertAtEnd(6);
        list.insertAtEnd(7);
        list.insertAtEnd(8);
        list.disp();
        int k = 4;
        SingleLinkedListNode reverseHead = reverseInGroupSize(list.head, k);
        list.setHead(reverseHead);
        list.disp();
    }
}

/*
1
8
1 2 2 4 5 6 7 8
4
* */

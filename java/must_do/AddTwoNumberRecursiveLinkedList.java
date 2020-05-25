package amzn.must_do;

import misc.SingleLinkedList;
import misc.SingleLinkedListNode;

/**
 * @author pushpanjay.kumar created on 23/3/20
 */
public class AddTwoNumberRecursiveLinkedList {

    private static SingleLinkedList result = null;
    private static int carry = 0;

    static int getLength(SingleLinkedListNode head){
        int count=0;
        while(head!=null){
            count++;
            head=head.next;
        }
        return count;
    }

    static SingleLinkedListNode traverseDiff(SingleLinkedListNode head, int diff){
        while(diff-->0){
            head=head.next;
        }
        return head;
    }

    static void addSameSize(SingleLinkedListNode head1, SingleLinkedListNode head2){
        if(head1 ==null){
            return;
        }
        addSameSize(head1.next, head2.next);
        int sum = carry + head1.data + head2.data;
        carry = sum/10;
        sum%=10;
        if(result == null){
            result = new SingleLinkedList(sum);
        } else {
            result.insertInBeg(sum);
        }
    }

    private static void propagateCarry(SingleLinkedListNode head1, SingleLinkedListNode cur){
        if(head1!=cur){
            propagateCarry(head1.next, cur);
            int sum = carry + head1.data;
            carry = sum/10;
            sum = sum%10;
            result.insertInBeg(sum);
        }
    }

    private static void addNum(SingleLinkedListNode head1, SingleLinkedListNode head2) {

        int s1 = getLength(head1);
        int s2 = getLength(head2);

        if(s1 == s2){
            addSameSize(head1, head2);
        } else{
            if(s1<s2){
                SingleLinkedListNode temp = head1;
                head1 = head2;
                head2 = temp;
            }
            int diff = Math.abs(s1-s2);
            SingleLinkedListNode cur = traverseDiff(head1, diff);
            addSameSize(cur, head2);

            propagateCarry(head1, cur);
        }

        if(carry>0){
            result.insertInBeg(carry);
        }
    }

    public static void main(String[] args) {
        SingleLinkedList num1 = new SingleLinkedList(5);
        num1.insertAtEnd(6);
//        num1.insertAtEnd(3);

        SingleLinkedList num2 = new SingleLinkedList(8);
        num2.insertAtEnd(4);
        num2.insertAtEnd(2);

        addNum(num1.head, num2.head);

        num1.disp();
        num2.disp();
        result.disp();
    }
}

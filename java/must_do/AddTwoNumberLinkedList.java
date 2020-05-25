package amzn.must_do;

import misc.SingleLinkedList;
import misc.SingleLinkedListNode;

/**
 * @author pushpanjay.kumar created on 23/3/20
 */
public class AddTwoNumberLinkedList {

    static SingleLinkedList addNum(SingleLinkedListNode num1, SingleLinkedListNode num2){
        int c=0;
        SingleLinkedList res = null;
        while(num1!=null || num2!=null){
            int sum = c + (num1!=null?num1.data:0) + (num2!=null?num2.data:0);
            c = sum>=10?1:0;
            sum=sum%10;
            if(res==null){
                res = new SingleLinkedList(sum);
            } else{
                res.insertAtEnd(sum);
            }
            if(num1!=null) {
                num1 = num1.next;
            }
            if(num2!=null) {
                num2 = num2.next;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SingleLinkedList num1 = new SingleLinkedList(4);
        num1.insertInBeg(5);

        SingleLinkedList num2 = new SingleLinkedList(3);
        num2.insertInBeg(4);
        num2.insertInBeg(5);

        SingleLinkedList res = addNum(num1.head, num2.head);

        num1.disp();
        num2.disp();
        res.disp();
    }
}

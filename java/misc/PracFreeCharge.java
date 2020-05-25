package misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author pushpanjay.kumar created on 24/3/20
 */
public class PracFreeCharge {

    void printRightView(DoublyLinkedListNode head){
        List<Integer> result = new ArrayList<>();

        if(head == null){
            return;
        }

        Queue<DoublyLinkedListNode> q =new LinkedList<>();
        q.add(head);
        //q.add(new DoublyLinkedListNode(Integer.MAX_VALUE));

        while(!q.isEmpty()){
            DoublyLinkedListNode node = q.remove();
            int n = q.size();
            for(int i=0;i<n;i++){

            }
            if(q.peek().data == Integer.MAX_VALUE){
                result.add(node.data);
            }

            if(node.data==Integer.MAX_VALUE){
                if(!q.isEmpty()){
                    q.add(new DoublyLinkedListNode(Integer.MAX_VALUE));
                }
                continue;
            }

            if(node.prev!=null){
                q.add(node.prev);
            }

            if(node.next!=null){
                q.add(node.next);
            }
        }

        result.forEach(l-> System.out.println(l));
    }
}
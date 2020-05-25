package misc;

/**
 * @author pushpanjay.kumar created on 19/2/20
 */

class CloneLinkedListNextRandomNode{
    int data;
    CloneLinkedListNextRandomNode next;
    CloneLinkedListNextRandomNode random;

    public CloneLinkedListNextRandomNode(int data) {
        this.data = data;
        this.next = null;
        this.random = null;
    }
}

public class CloneLinkedListNextRandomPointer {
    CloneLinkedListNextRandomNode head;

    public CloneLinkedListNextRandomPointer(CloneLinkedListNextRandomNode head) {
        this.head = head;
    }

    void push(int data){
        // adding new node at begining
        CloneLinkedListNextRandomNode node = new CloneLinkedListNextRandomNode(data);
        node.next = head;
        head = node;
    }

    private void disp(){
        System.out.println();
        CloneLinkedListNextRandomNode temp = this.head;
        while(temp!=null){
            System.out.print(temp.data +", random node "+ (temp.random == null?" ":temp.random.data) + "-> ");
            temp = temp.next;
        }
    }

    public CloneLinkedListNextRandomPointer clone(){
        if(head == null){
            return null;
        }

        CloneLinkedListNextRandomNode current = head;
        while(current!=null){
            CloneLinkedListNextRandomNode node = new CloneLinkedListNextRandomNode(current.data);
            node.next = current.next;
            current.next = node;
            current = current.next.next;
        }

        current = head;
        while(current!=null){
            current.next.random = current.random == null?null:current.random.next;
            current = current.next.next;
        }

        current = head;
        CloneLinkedListNextRandomPointer dupList = new CloneLinkedListNextRandomPointer(current.next);

        CloneLinkedListNextRandomNode dupCurrent = dupList.head;

        while (current!=null){
            current.next = current.next.next;
            dupCurrent.next = current.next;
            current = current.next;
            dupCurrent = dupCurrent.next;
        }

        return dupList;
    }

    public static void main(String[] args) {
        CloneLinkedListNextRandomPointer list = new CloneLinkedListNextRandomPointer(new CloneLinkedListNextRandomNode(5));
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);

        list.head.random = list.head.next.next;
        list.head.next.random = list.head.next.next.next;
        list.head.next.next.random = list.head.next.next.next.next;
        list.head.next.next.next.random = list.head.next.next.next.next.next;
        list.head.next.next.next.next.random = list.head.next;

        CloneLinkedListNextRandomPointer dup = list.clone();
        list.disp();
        dup.disp();
        System.out.println();
    }
}

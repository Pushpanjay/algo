package misc;

/**
 * @author pushpanjay.kumar created on 22/3/20
 */
public class SingleLinkedList {
    public SingleLinkedListNode head;

    public void setHead(SingleLinkedListNode head) {
        this.head = head;
    }

    public SingleLinkedList(int data) {
        this.head = new SingleLinkedListNode(data);
    }

    public void insertInBeg(int data){
        SingleLinkedListNode node = new SingleLinkedListNode(data);
        node.next = this.head;
        this.head = node;
    }

    public void insertAtEnd(int data){
        SingleLinkedListNode node = new SingleLinkedListNode(data);
        SingleLinkedListNode temp = this.head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next = node;
    }

    public void disp(){
        System.out.println();
        SingleLinkedListNode temp = this.head;
        while(temp!=null){
            System.out.print(temp.data + "->");
            temp=temp.next;
        }
    }

    void reverseLinkedList(){
        SingleLinkedListNode prev, current, next;
        prev = null;
        current = head;
        while(current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        list.insertAtEnd(4);
        list.insertInBeg(0);
        list.disp();
        list.reverseLinkedList();
        list.disp();
    }
}

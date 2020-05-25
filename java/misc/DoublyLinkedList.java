package misc;

/**
 * @author pushpanjay.kumar created on 24/3/20
 */
public class DoublyLinkedList {
    private DoublyLinkedListNode head;

    public DoublyLinkedList(int data) {
        this.head = new DoublyLinkedListNode(data);
    }

    public DoublyLinkedListNode getHead() {
        return head;
    }

    public void setHead(DoublyLinkedListNode head) {
        this.head = head;
    }

    public void insertInBeg(int data){
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);
        node.setNext(this.head);
        this.head.setPrev(node);
        this.head = node;
    }

    public void insertInEnd(int data){
        DoublyLinkedListNode temp = this.head;
        while(temp.getNext()!=null){
            temp = temp.getNext();
        }
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);
        node.setPrev(temp);

    }
}

/**
 * @author pushpanjay.kumar created on 18/2/20
 */

public class CountNumofSubTreeWithSumS {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = this.right = null;
        }
    }

    static class INT{
        int v;

        INT(int v) {
            this.v = v;
        }
    }

    private static Node getNode(int data){
        return new Node(data);
    }
    private static int countSubtreesWithSumX(Node root, INT count, int sum){
        if(root==null)
            return 0;
        int ls = countSubtreesWithSumX(root.left, count, sum);
        int rs = countSubtreesWithSumX(root.right, count, sum);

        int sumNode = ls+rs+root.data;
        if(sumNode == sum) {
            count.v++;
        }
        return sumNode;
    }

    private static int countSubtreesWithSumXUtil(Node root, int sum) {
        if(root==null)
            return 0;
        INT count = new INT(0);
        int ls = countSubtreesWithSumX(root.left, count, sum);
        int rs = countSubtreesWithSumX(root.right, count, sum);

        if(ls+rs+root.data == sum)
            count.v++;
        return count.v;
    }

    //TC: O(n)
    public static void main(String[] args) {
        Node root = getNode(5);
        root.left = getNode(-10);
        root.right = getNode(3);
        root.left.left = getNode(9);
        root.left.right = getNode(8);
        root.right.left = getNode(-4);
        root.right.right = getNode(7);

        int sum = 7;

        System.out.println(countSubtreesWithSumXUtil(root, sum));
    }
}

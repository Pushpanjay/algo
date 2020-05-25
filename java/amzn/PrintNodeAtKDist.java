package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class PrintNodeAtKDist {

    static void printNodeAtKDist(BinaryTreeNode root, int k){
        if(root == null){
            return;
        }
        if(k==0){
            System.out.print(root.data + " ");
            return;
        }
        printNodeAtKDist(root.left, k-1);
        printNodeAtKDist(root.right, k-1);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTreeNode root = binaryTree.buildTree();
        int k=2;
        printNodeAtKDist(root, k);
    }
}

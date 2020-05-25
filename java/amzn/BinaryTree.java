package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */

public class BinaryTree {

    static BinaryTreeNode getNewNode(int data){
        return new BinaryTreeNode(data);
    }

    static BinaryTreeNode buildTree(){
        BinaryTreeNode root = getNewNode(5);
        root.left = getNewNode(2);
        root.right = getNewNode(6);
        root.left.left = getNewNode(1);
        root.left.right = getNewNode(4);
        root.left.right.left = getNewNode(3);
        root.right.right = getNewNode(8);
        root.right.right.left = getNewNode(7);
        root.right.right.right = getNewNode(9);
        return root;
    }
}

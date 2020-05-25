package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class HeightOfABinaryTree {

    static int heightOfBinaryTree(BinaryTreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(heightOfBinaryTree(root.left), heightOfBinaryTree(root.right));
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTreeNode root = binaryTree.buildTree();
        System.out.println(heightOfBinaryTree(root));
    }
}

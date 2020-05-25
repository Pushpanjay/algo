package amzn;

/**
 * @author pushpanjay.kumar created on 22/3/20
 */
public class TreeIsomorphism {

    //tc: O(min(m, n)); m,n are number of nodes in tree1 and tree2 resp
    static boolean isIsomorphic(BinaryTreeNode tree1, BinaryTreeNode tree2){
        if(tree1==null && tree2==null){
            return true;
        }
        if(tree1==null || tree2==null) {
            return false;
        }
        if(tree1.data!=tree2.data) {
            return false;
        }
        return (isIsomorphic(tree1.left, tree2.left) && isIsomorphic(tree1.right, tree2.right))
                || (isIsomorphic(tree1.left, tree2.right) && isIsomorphic(tree1.right, tree2.left));
    }

    public static void main(String[] args) {
        BinaryTreeNode tree1 = new BinaryTreeNode(1);
        tree1.left = new BinaryTreeNode(2);
        tree1.right = new BinaryTreeNode(3);
        tree1.left.left = new BinaryTreeNode(4);
        tree1.left.right = new BinaryTreeNode(5);
        tree1.right.left = new BinaryTreeNode(6);
        tree1.left.right.left = new BinaryTreeNode(7);
        tree1.left.right.right = new BinaryTreeNode(8);

        BinaryTreeNode tree2 = new BinaryTreeNode(1);
        tree2.left = new BinaryTreeNode(3);
        tree2.right = new BinaryTreeNode(2);
        tree2.right.left = new BinaryTreeNode(4);
        tree2.right.right = new BinaryTreeNode(5);
        tree2.left.right = new BinaryTreeNode(6);
        tree2.right.right.left = new BinaryTreeNode(8);
        tree2.right.right.right = new BinaryTreeNode(7);

        System.out.println(isIsomorphic(tree1, tree2));
    }
}

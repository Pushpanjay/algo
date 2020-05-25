package amzn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class DiffBetweenSumsOfOddlevelEvelLevel {

    // iterative level order traversal
    static int diffBetweenOddEvenLevelOrder(BinaryTreeNode root){
        if(root==null) {
            return 0;
        }
        int oddSum=0;
        int evenSum=0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        boolean isOddLevel=true;
        queue.add(root);
        queue.add(new BinaryTreeNode(Integer.MIN_VALUE));
        while(!queue.isEmpty()){
            BinaryTreeNode node = queue.remove();
            if(node.data == Integer.MIN_VALUE) {
                isOddLevel = !isOddLevel;
                if(!queue.isEmpty()) {
                    queue.add(new BinaryTreeNode(Integer.MIN_VALUE));
                }
            }

            if(isOddLevel) {
                oddSum += node.data;
            }
            else {
                evenSum += node.data;
            }


            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return oddSum-evenSum;
    }

    // recursive traversal
    static int diffBetweenOddEvenRecursive(BinaryTreeNode root){
        if(root==null){
            return 0;
        }
        return root.data - diffBetweenOddEvenRecursive(root.left)-diffBetweenOddEvenRecursive(root.right);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTreeNode root = binaryTree.buildTree();
        System.out.println(diffBetweenOddEvenLevelOrder(root));
        System.out.println(diffBetweenOddEvenRecursive(root));
    }
}

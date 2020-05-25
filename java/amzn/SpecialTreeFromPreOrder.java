package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */

class Index{
    int index=0;

    public Index(int index) {
        this.index = index;
    }
}

public class SpecialTreeFromPreOrder {

    static BinaryTreeNode buildTree(int []pre, char []preLN, Index index) {
        if (index.index == pre.length){
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(pre[index.index]);
        char temp = preLN[index.index];
        index.index++;
        if(temp=='N'){
            node.left = buildTree(pre, preLN, index);
            node.right = buildTree(pre, preLN, index);
        }
        return node;
    }

    public static void main(String[] args) {
        int pre[] = new int[]{10, 30, 20, 5, 15};
        char preLN[] = new char[]{'N', 'N', 'L', 'L', 'L'};

        BinaryTreeNode root = buildTree(pre, preLN, new Index(0));
        System.out.println("");
    }
}

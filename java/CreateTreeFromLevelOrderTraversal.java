import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pushpanjay.kumar created on 18/2/20
 */

class Node{
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class CreateTreeFromLevelOrderTraversal {



    private static Node buildTree(String s){
        if(s.length()==0 || s.charAt(0) == 'N'){
            return null;
        }
        String[] ip = s.split("\\s");
        Node root = new Node(Integer.parseInt(ip[0]));

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int i=1;
        while(!queue.isEmpty() && i<ip.length){
            Node currNode = queue.peek();
            queue.remove();

            String currVal = ip[i];

            if(!"N".equals(currVal)){
                currNode.left = new Node(Integer.parseInt(ip[i]));
                queue.add(currNode.left);
            }
            i++;
            if(i >= ip.length){
                break;
            }
            currVal = ip[i];
            if(!"N".equals(currVal)){
                currNode.right = new Node(Integer.parseInt(ip[i]));
                queue.add(currNode.right);
            }

            i++;
        }

        return root;
    }

    static void printInorder(Node root){
        if(root==null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            String s = br.readLine();
            Node root = buildTree(s);
            printInorder(root);
        }
    }
}

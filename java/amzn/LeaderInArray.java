package amzn;

/**
 * @author pushpanjay.kumar created on 20/3/20
 */
public class LeaderInArray {

    //tc: O(n)
    static void printLeaders(int []a){
        int max = a[a.length-1];
        System.out.print(max + " ");
        for(int i=a.length-2;i>=0;i--){
            if(a[i]>max){
                max = a[i];
                System.out.print(max + " ");
            }
        }
    }

    public static void main(String ...s){
        int arr[] = new int[]{16, 17, 4, 3, 5, 2};
        printLeaders(arr);
    }
}

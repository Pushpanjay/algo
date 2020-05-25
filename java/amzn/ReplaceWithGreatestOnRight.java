package amzn;

/**
 * @author pushpanjay.kumar created on 20/3/20
 */
public class ReplaceWithGreatestOnRight {

    static void replaceWithGreatest(int a[]){
        int greatest = -1;
        for(int i=a.length-1;i>=0;i--){
            int tempGreatest = a[i]>greatest?a[i]:greatest;
            a[i] = greatest;
            greatest = tempGreatest;
        }
    }

    static void disp(int arr[]){
        System.out.println();
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String st[]){
        int arr[] = new int[]{16, 17, 4, 3, 5, 2};
        replaceWithGreatest(arr);
        disp(arr);
    }
}

package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class PairWithMaxProduct {

    static void maxProductPair(int []a){
        int n = a.length;
        if(n<2){
            System.out.println("No Pair Exists");
            return;
        }
        int max1, max2;
        if(a[0]>a[1]){
            max1 = a[0];
            max2 = a[1];
        } else{
            max1 = a[1];
            max2 = a[0];
        }
        for(int i=2;i<n;i++){
            if(a[i]>max1){
                max2 = max1;
                max1 = a[i];
            } else if(a[i]>max2){
                max2 = a[i];
            }
        }
        System.out.println("{" + max1 + ", " + max2 + "}");
    }
    public static void main(String[] args) {
        int arr[] = {1, 4, 3, 6, 7, 0};
        maxProductPair(arr);
    }
}

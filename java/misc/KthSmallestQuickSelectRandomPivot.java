package misc;

/**
 * @author pushpanjay.kumar created on 16/2/20
 */
// worst case O(n^2), but works in O(n) on average
public class KthSmallestQuickSelectRandomPivot {

    private static void swap(Integer[] a, int i, int j){
        int temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    private static int partition(Integer[] arr, int l, int r){
        int i = l-1;
        int x = arr[r];
        for(int j=l;j<r;j++){
            if(arr[j]<=x){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, r);
        return i+1;
    }

    private static int randomPartition(Integer[] a, int l, int r){
        int n = r-l+1;
        int pivot = (int)(Math.random() * (n-1));
        swap(a, pivot+1, r);
        return partition(a, l, r);
    }

    private static int kthSmallest(Integer[] arr, int l, int r, int k){
        if(k>0 && k<=r-l+1 ){
            int p = randomPartition(arr, l, r);
            if(p-l == k-1){
                return arr[p];
            } else if(p-l > k-1){
                return kthSmallest(arr, l, p-1, k);
            } else{
                return kthSmallest(arr, p+1, r, k-(p+1-l));
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args){
        Integer arr[] = new Integer[]{12, 3, 5, 7, 4, 19, 26};
        int k=3;
        Math.random();
        System.out.println(kthSmallest(arr, 0, arr.length-1, k));
    }

}

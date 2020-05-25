import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pushpanjay.kumar created on 17/2/20
 */
class MinHeapNode{
    int element;
    int i; //array index of which element is part of
    int j; //next element index of array

    public MinHeapNode(int element, int i, int j) {
        this.element = element;
        this.i = i;
        this.j = j;
    }
}

class MergeKSortedArray {
    MinHeapNode[] harr;
    int h_s;

    public MergeKSortedArray(MinHeapNode[] harr, int h_s) {
        this.harr = harr;
        this.h_s = h_s;
        for(int i=(h_s-2)/2; i>=0;i--){
            minHeapify(i);
        }
    }

    int parent(int i){
        return (i-1)/2;
    }

    int left(int i){
        return 2*i+1;
    }

    int right(int i){
        return 2*i+2;
    }

    void swap(MinHeapNode[] a, int i, int j){
        MinHeapNode temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    void minHeapify(int i){
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if(l<this.h_s && this.harr[l].element < harr[smallest].element){
            smallest=l;
        }
        if(r<this.h_s && this.harr[r].element < harr[smallest].element){
            smallest=r;
        }
        if(smallest!=i){
            swap(harr, i, smallest);
            minHeapify(smallest);
        }
    }

    MinHeapNode getMin(){
        if(h_s<=0){
            System.out.println("Heap Underflow");
            return null;
        }
        return harr[0];
    }

    void replacMin(MinHeapNode node){
        harr[0] = node;
        minHeapify(0);
    }

    static void disp(List<Integer> arr){
        System.out.println();
        arr.forEach(System.out::println);
    }

    static List<Integer> mergeKSortedArray(int[][] arr, int k){
        MinHeapNode[] harr = new MinHeapNode[k];
        int resultLength = 0;
        for(int i=0;i<arr.length;i++){
            MinHeapNode node = new MinHeapNode(arr[i][0], i,1);
            harr[i] = node;
            resultLength+=arr[i].length;
        }

        MergeKSortedArray mh = new MergeKSortedArray(harr, k);
        int[] resultArray = new int[resultLength];

        for(int i=0;i<resultLength;i++){
            MinHeapNode root = mh.getMin();
            resultArray[i]= root.element;

            if(root.j < arr[root.i].length){
                root.element = arr[root.i][root.j];
                root.j++;
            } else{
                root.element = Integer.MAX_VALUE;
            }

            mh.replacMin(root);
        }

        return Arrays.stream(resultArray).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int t = Integer.parseInt(br.readLine());
        int t=1;
        while(t-->0){
            //int k = Integer.parseInt(br.readLine());
            int[][] a = {{2, 6, 12, 34}, {1, 9, 20, 1000}, {23, 34, 90, 2000}};
            /*int[][] a=new int[k][k];
            String[] inpar = br.readLine().split("\\s");
            int p=0;
            for(int i=0;i<k;i++){
                for(int j=0;j<k;j++){
                    a[i][j] = Integer.parseInt(inpar[p++]);
                }
            }*/
            disp(mergeKSortedArray(a, a.length));
        }
    }
}

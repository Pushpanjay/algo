package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KthSmallestElementMinHeap{

    private static int kthSmallest(int arr[], int k){
        MinHeap heap = new MinHeap(arr, arr.length);
        while(k>1){
            heap.extractMin();
            k--;
        }
        return heap.extractMin();
    }

    // TC = O(n + klogN)
    public static void main(String... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        
        int arr[] = new int[s];
        int i=0;
        String in[] = br.readLine().trim().split(",");
        for(String sin:in){
            arr[i++] = Integer.parseInt(sin.trim());
        }

        int k = Integer.parseInt(br.readLine());

        int r = kthSmallest(arr, k);
        System.out.println(r);
    }
}

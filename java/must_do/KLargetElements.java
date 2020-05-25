package must_do;

import misc.MaxHeap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author pushpanjay.kumar created on 22/3/20
 */
public class KLargetElements {


    static int[] fetchKLargestElement(int []a, int k){
        MaxHeap mh = new MaxHeap(a, a.length);
        int []rt = new int[k];
        for(int i=0;i<k;i++){
            rt[i] = mh.extractMax();
        }
        return rt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            String []s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            String []sarr = br.readLine().trim().split(" ");
            int []arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(sarr[i]);
            }
            int []rt = fetchKLargestElement(arr, k);
            for(int i=0;i<rt.length;i++){
                System.out.print(rt[i] + " ");
            }
            System.out.println();
        }
    }
}

/* sample input:
1
7 3
1 23 12 9 30 2 50
*/
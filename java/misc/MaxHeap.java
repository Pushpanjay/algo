/**
 * @author pushpanjay.kumar created on 22/3/20
 */
public class MaxHeap {
    int arr[];
    int c;
    int s;

    public MaxHeap(int[] arr, int s) {
        this.arr = arr;
        this.s = s;
        for(int i=(s-2)/2;i>=0;i--){
            maxHeapify(i);
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

    void maxHeapify(int i){
        int l = left(i);
        int r = right(i);
        int largest = i;
        if(l<s && arr[l]>arr[largest]){
            largest = l;
        }
        if(r<s && arr[r]>arr[largest]){
            largest=r;
        }

        if(largest!=i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(largest);
        }
    }

    int extractMax(){
        if(s==0){
            return Integer.MIN_VALUE;
        }
        int root = arr[0];
        if(s>1){
            arr[0] = arr[s-1];
            maxHeapify(0);
        }
        s--;
        return root;
    }

    int getMin(){
        if(s==0){
            return Integer.MIN_VALUE;
        }
        return arr[0];
    }
}

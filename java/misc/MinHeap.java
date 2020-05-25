
class MinHeap{
    int arr[];
    int c;
    int s;

    MinHeap(int arr[], int s){
        this.arr = arr;
        this.s=s;
        for(int i = (s-2)/2;i>=0;i--){
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

    void minHeapify(int i){
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if(l<this.s && arr[l]<arr[smallest]){
            smallest = l;
        }
        if(r<this.s && this.arr[r]<this.arr[smallest]){
            smallest = r;
        }

        if(smallest!=i){
            int temp = arr[i];
            arr[i]=arr[smallest];
            arr[smallest]=temp;
            minHeapify(smallest);
        }
    }

    int extractMin(){
        if(s==0){
            return Integer.MAX_VALUE;
        }
        int root = arr[0];
        if(s>1){
            arr[0] = arr[s-1];
            minHeapify(0);
        }
        s--;
        return root;
    }
}
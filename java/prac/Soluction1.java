package prac;

/**
 * @author pushpanjay.kumar created on 13/4/20
 */
class MaxHeap{
    int []a;
    int s;

    public MaxHeap(int []a, int s){
        this.a = a;
        this.s = s;
        for(int i=(s-2)/2;i>=0;i--){
            maxHeapifySink(i);
        }
    }

    int getHeapSize(){
        return s;
    }

    int left(int i){
        return 2*i+1;
    }

    int right(int i){
        return 2*i+2;
    }

    int parent(int i){
        return (i-1)/2;
    }

    void maxHeapifySink(int i){
        int l = left(i);
        int r = right(i);
        int largest = i;
        if(l<s && a[l]>a[largest])
            largest=l;
        if(r<s && a[r]>a[largest])
            largest=r;

        if(largest!=i){
            int temp = a[largest];
            a[largest] = a[i];
            a[i] = temp;
            maxHeapifySink(largest);
        }
    }

    int getMax(){
        if(s==0)
            throw new RuntimeException("UnderFlow");
        return a[0];
    }

    int extractMax(){
        if(s==0)
            throw new RuntimeException("UnderFlow");

        int r = a[0];
        if(s>1){
            a[0] = a[s-1];
            maxHeapifySink(0);
        }
        s--;
        return r;
    }

    void maxHeapifyBubbleUp(int i){
        int parent = parent(i);
        if(parent >=0 && a[parent]<a[i]){
            int temp = a[parent];
            a[parent] = a[i];
            a[i] = temp;
            maxHeapifyBubbleUp(parent);
        }
    }

    void insertNode(int data){
        s++;
        a[s-1] = data;
        maxHeapifyBubbleUp(s-1);
    }
}

class Solution1 {
    public int lastStoneWeight(int[] stones) {
        MaxHeap heap = new MaxHeap(stones, stones.length);
        while(heap.getHeapSize()>1){
            int y = heap.extractMax();
            int x = heap.extractMax();
            if(x < y){
                heap.insertNode(y-x);
            }
        }
        if(heap.getHeapSize() == 1){
            return heap.getMax();
        } else{
            return 0;
        }
    }

    public static void main(String[] args) {
        int []stones = new int[]{2,7,4,1,8,1};
        Solution1 s = new Solution1();
        System.out.println(s.lastStoneWeight(stones));
    }
}

package amzn;

/**
 * @author pushpanjay.kumar created on 19/3/20
 */
public class MatricChainMultiplicationRecursive {

    // tc: O(2^n)
    static int matrixChainOrder(int p[], int i, int j){
        if(i==j)
            return 0;

        int min = Integer.MAX_VALUE;

        for(int k=i;k<j;k++) {
            int count = matrixChainOrder(p, i, k) + matrixChainOrder(p,k+1, j) + p[i - 1] * p[k] * p[j] ;
            if(count<min){
                min = count;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int []p= new int[]{1,2,3,4,3};
        System.out.println("Mininum of multiplication " + matrixChainOrder(p, 1, p.length-1));
    }
}

package amzn;

/**
 * @author pushpanjay.kumar created on 20/3/20
 */
public class MatrixChainMultiplicationDP {

    // // tc: O(n^3), AS: O(n^2)
    static int minNumbeOfMultiplication(int p[], int n){
        int m[][] = new int[n][n];
        int i, j, k,q,l;
        for(i=1; i<n;i++){
            m[i][i] = 0;
        }

        for(l=2;l<n;l++){
            for(i=1;i<n-l+1;i++){
                j = i+l-1;
                if(j == n)
                    continue;
                m[i][j] = Integer.MAX_VALUE;
                for(k=i; k<=j-1;k++){
                    q = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
                    if(q<m[i][j]){
                        m[i][j] = q;
                    }
                }
            }
        }
        return m[1][n-1];
    }

    public static void main(String ...s){
        int []p = new int[]{1, 2, 3, 4};
        System.out.println("Min num of multiplication is :" + minNumbeOfMultiplication(p, p.length));
    }
}

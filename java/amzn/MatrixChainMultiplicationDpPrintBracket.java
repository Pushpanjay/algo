package amzn;

/**
 * @author pushpanjay.kumar created on 20/3/20
 */
public class MatrixChainMultiplicationDpPrintBracket {
    static char name = 'A';

    // tc: O(n^3), AS: O(n^2)
    static void printParenthesis(int i, int j, int[][] bracket){
        if(i==j){
            System.out.print(name);
            name++;
            return;
        }
        System.out.print("(");
        printParenthesis(i, bracket[i][j], bracket);
        printParenthesis(bracket[i][j]+1, j, bracket);
        System.out.print(")");
    }

    static int minNumbeOfMultiplication(int []p, int n){
        int [][]m = new int[n][n];
        int i, j, k, q,l;
        for(i=1;i<n;i++){
            m[i][i] = 0;
        }
        int [][]bracket = new int[n][n];

        for(l=2;l<n;l++){
            for(i=1;i<n-l+1;i++){
                j = i+l-1;
                if(j==n)
                    continue;
                m[i][j] = Integer.MAX_VALUE;
                for(k=i;k<j;k++){
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if(q<m[i][j]){
                        m[i][j] = q;
                        bracket[i][j] = k;
                    }
                }
            }
        }
        System.out.println("Array optimal bracket");
        printParenthesis(1, n-1, bracket);
        System.out.println();
        return m[1][n-1];
    }

    public static void main(String ...s){
        int []p = new int[]{40, 20, 30, 10, 30};
        System.out.println(minNumbeOfMultiplication(p, p.length));
    }
}

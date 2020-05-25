package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class PrintAllPossiblePathFromTopLeft {

    static void disp(int path[], int n){
        System.out.println();
        for(int i=0;i<n;i++){
            System.out.print(path[i] + " ");
        }
    }

    static void printMatrix(int mat[][], int m, int n, int i, int j, int path[], int idx){
        path[idx++] = mat[i][j];
        if(i+1<m){
            printMatrix(mat, m, n, i+1, j, path, idx);
        }
        if(j+1<n){
            printMatrix(mat, m, n, i, j+1, path, idx);
        }
        if(i==m-1 && j==n-1){
            disp(path, idx);
        }
    }


    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int mat[][] = { { 1, 2, 3 },
                { 4, 5, 6 } };
        int maxLength = m+n-1;
        printMatrix(mat, m, n, 0, 0, new int[maxLength], 0);
    }
}

package misc;

/**
 * @author pushpanjay.kumar created on 19/2/20
 */
public class LargestLengthAreaBooleanMatrix {
    private static int row;
    private static int col;
    private static int count;

    private static boolean isSafe(int[][] m, int i, int j, boolean[][] visited){
        return i>=0 && i<row && j>=0 && j<col && m[i][j] ==1 && !visited[i][j];
    }

    private static void dfs(int[][] m, int i, int j, boolean[][] visited){
        int[] rowNbr = new int[]{-1, -1, -1, 0,0,1,1,1};
        int[] colNbr = new int[]{-1, 0, 1,-1, 1,-1,0,1};

        visited[i][j] = true;

        for(int p=0;p<8;p++){
            if(isSafe(m, i+rowNbr[p], j+colNbr[p], visited)){
                count++;
                dfs(m, i+rowNbr[p], j+colNbr[p], visited);
            }
        }
    }

    private static int largestRegion(int[][] m){
        boolean[][] visited = new boolean[row][col];
        int result=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(m[i][j] == 1 && !visited[i][j]){
                    count=1;
                    dfs(m, i, j, visited);
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int M[][] = { {0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1}};
        row = 4;
        col = 5;
        System.out.println(largestRegion(M));
    }
}

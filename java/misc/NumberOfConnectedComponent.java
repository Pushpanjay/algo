package misc;

/**
 * @author pushpanjay.kumar created on 18/2/20
 */
// problem number of islands (using DFS)
public class NumberOfConnectedComponent {

    static int r;
    static int c;

    private static boolean isSafe(int[][] m, int r, int c, boolean[][] visited){
        return r>=0 && c>=0 && r< NumberOfConnectedComponent.r && c< NumberOfConnectedComponent.c && m[r][c] ==1 && !visited[r][c];
    }

    private static void dfs(int[][] m, int r, int c, boolean[][] visited){
        int[] rowNbr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        visited[r][c] = true;

        for(int p=0;p<8;p++){
            if(isSafe(m,r+ rowNbr[p], c+colNbr[p], visited)){
                dfs(m, r+ rowNbr[p], c+colNbr[p], visited);
            }
        }
    }



    private static int countConnectedComponent(int[][] m){
        boolean[][] visited = new boolean[r][c];
        int count = 0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(m[i][j] == 1 && !visited[i][j]){
                    dfs(m, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int m[][] = new int[][]{{ 1, 1, 0, 0, 0 },
                                { 0, 1, 0, 0, 1 },
                                { 1, 0, 0, 1, 1 },
                                { 0, 0, 0, 0, 0 },
                                { 1, 0, 1, 0, 1 }};
        r = m.length;
        c = m[0].length;
        System.out.println(countConnectedComponent(m));
    }

}

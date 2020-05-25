package prac;

/**
 * @author pushpanjay.kumar created on 21/4/20
 */
class Solution {

    private boolean isSafe(char[][] grid, boolean[][] visited, int r, int c, int i, int j){
        return i>=0 && i<r && j>=0 && j<c && grid[i][j] == 1 && visited[i][j] == false;
    }

    private void dfs(char[][] grid, boolean[][] visited, int r, int c, int i, int j){
        visited[i][j] = true;

        int[] rows = new int[]{0,0,-1,1};
        int[] colmns = new int[]{-1,1,0,0};

        for(int k=0;k<4;k++){
            if(isSafe(grid, visited, r, c, i + rows[k], j + colmns[k])){
                dfs(grid, visited, r, c, i + rows[k], j + colmns[k]);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int r = grid.length;
        if(r == 0)
            return 0;
        int c = grid[0].length;

        boolean[][] visited = new boolean[r][c];
        System.out.println(r + " " + c);

        int count=0;

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                System.out.println(visited[i][j] + " " + grid[i][j]);
                if(visited[i][j] == false && grid[i][j] == 1){
                    System.out.println("count" + count);
                    count++;
                    dfs(grid, visited, r, c, i, j);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,1,1,1,0},{1,1,0,1,0}};
        Solution s = new Solution();
        System.out.println(s.numIslands(null));
    }
}

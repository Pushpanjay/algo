package amzn;

/**
 * @author pushpanjay.kumar created on 21/3/20
 */
public class CountAllPossiblePathFromTopLeft {
    // tc: exponential
    static int countAllPossiblePathRecursive(int m, int n){
        if(m==1 || n==1)
            return 1;
        return countAllPossiblePathRecursive(m-1, n) + countAllPossiblePathRecursive(m, n-1);
    }

    //tc:O(mn); as:O(mn)
    static int coutnAllPossiblePathDP(int m, int n){
        int count[][] = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(i==1 || j==1){
                    count[i][j] = 1;
                    continue;
                }
                count[i][j] = count[i-1][j] + count[i][j-1];
            }
        }
        return count[m][n];
    }

    //tc:O(mn); as:O(n)
    static int coutnAllPossiblePathDPSpaceOptimized(int m, int n){
        int dp[] = new int[n+1];
        dp[1] = 1;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                dp[j]+=dp[j-1];
            }
        }
        return dp[n];
    }

    public static void main(String ...s){
        System.out.println(countAllPossiblePathRecursive(3, 3));
        System.out.println(coutnAllPossiblePathDP(3, 3));
        System.out.println(coutnAllPossiblePathDPSpaceOptimized(3, 3));
    }
}

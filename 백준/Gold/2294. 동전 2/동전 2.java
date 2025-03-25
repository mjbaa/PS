import java.util.*;
import java.io.*;



public class Main {
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        coins = new int[n+1];
        dp = new int[n+1][k+1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for(int i=1;i<=n;i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1;i<=n;i++){
            for(int j=0;j<=k;j++){
                if(j >= coins[i] && dp[i][j - coins[i]] != Integer.MAX_VALUE){
                    dp[i][j] = Math.min(dp[i][j-coins[i]] + 1, dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[n][k] == Integer.MAX_VALUE ? -1 : dp[n][k]);

    }
}

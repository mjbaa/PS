import java.util.*;
import java.io.*;

public class Main {
    static int n,k;
    static int[] coins;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n+1];
        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n+1][k+1];
        for(int i=0;i<=n;i++){
            dp[i][0] = 1;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                dp[i][j] += dp[i-1][j];
                if(j-coins[i] >= 0){
                    dp[i][j] += dp[i][j-coins[i]];
                }
            }
        }

        System.out.println(dp[n][k]);


    }
}

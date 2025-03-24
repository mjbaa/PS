import java.util.*;
import java.io.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=t;tc++) {
            int n = Integer.parseInt(br.readLine());

            int[] coins = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }


            int m = Integer.parseInt(br.readLine());

            int[][] dp = new int[n+1][m+1];
            dp[0][0] = 1;


            for(int i=1;i<=n;i++){
                for(int j=0;j<=m;j++){
                    if(j >= coins[i]){
                        dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]];
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            System.out.println(dp[n][m]);

        }
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp = new int[4][10001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        dp[1][0] = dp[2][0] = dp[3][0] = 1;

        for (int k = 1; k <= 3; k++) {
            for (int n = 1; n <= 10000; n++) {
                if (n - k >= 0){
                    dp[k][n] += dp[k][n - k];
                }

                if (k > 1){
                    dp[k][n] += dp[k - 1][n];
                }

            }
        }

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[3][n]).append('\n');
        }
        System.out.print(sb);
    }
}

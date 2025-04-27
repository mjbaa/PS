import java.io.*;
import java.util.*;

public class Main {
    static long[] dp = new long[101];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        
        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }
        
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        
        System.out.print(sb);
    }
}

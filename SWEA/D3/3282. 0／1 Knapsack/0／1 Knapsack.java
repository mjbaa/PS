import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] weights = new int[n];
            int[] values = new int[n];
            
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                weights[i] = Integer.parseInt(st.nextToken());
                values[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] dp = new int[k + 1];
            
            for (int i = 0; i < n; i++) {
                int weight = weights[i];
                int value = values[i];
                
                for (int w = k; w >= weight; w--) { 
                    dp[w] = Math.max(dp[w], dp[w - weight] + value);
                }
            }
            
            sb.append("#").append(tc).append(" ").append(dp[k]).append("\n");
        }
        
        System.out.println(sb);
    }
}

import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n,m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        int[][] data = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        
        for(int i=1;i<=n;i++) {
        	String line = br.readLine();
        	for(int j=1;j<=m;j++) {
        		data[i][j] = line.charAt(j-1)-'0';
        	}
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=0;i<=n;i++) {
        	for(int j=0;j<=m;j++) {
        		if(data[i][j] == 1) {
        			
        			int min = Math.min(dp[i-1][j], dp[i][j-1]);
        			min = Math.min(min, dp[i-1][j-1]);
        			
        			dp[i][j] = min + 1;
        			max = Math.max(max, min+1);
        		}
        	}
        }
        
        System.out.println(max * max);
        
    }
}

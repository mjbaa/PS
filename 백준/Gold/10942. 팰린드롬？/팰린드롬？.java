import java.io.*;
import java.util.*;

public class Main {

	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        
        int[] data = new int[n];
        for(int i=0;i<n;i++) {
        	data[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean[][] dp = new boolean[n][n];
        for(int i=0;i<n;i++) {
        	dp[i][i] = true;
        }
        
        
        for (int i = 0; i < n-1; i++) {
            if (data[i] == data[i+1]) {
                dp[i][i+1] = true;
            }
        }
        
        for(int len = 3; len <=n;len++) {
        	for(int i=0;i<=n-len;i++) {
        		if(data[i] == data[i+len-1] && dp[i+1][i+len-2]) {
        			dp[i][i+len-1] = true;
        		}
        	}
        }
        
        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken())-1;
        	int end = Integer.parseInt(st.nextToken())-1;
        	
        	if(dp[start][end]) {
//        		System.out.println(1);
        		sb.append("1\n");
        	}else {
        		sb.append("0\n");
        	}
        	

        }
        System.out.println(sb);

    }
}

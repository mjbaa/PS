import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        
        int[] mem = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
        	mem[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] cost = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
        	cost[i] = Integer.parseInt(st.nextToken());
        }
        
        
        
        int[] dp = new int[10001];//비용 i에서 얻을 수 있는 최대 mem
        for(int i=0;i<n;i++) {
        	for(int j=10000;j>=cost[i];j--) {
        		dp[j] = Math.max(dp[j], dp[j-cost[i]] + mem[i]);
        	}
        }
        
        for(int i=0;i<10001;i++) {
        	if(dp[i] >= m) {
        		System.out.println(i);
        		break;
        	}
        }
    }
}

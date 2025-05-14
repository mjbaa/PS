import java.io.*;
import java.util.*;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=t;tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(br.readLine());
			
			int[] coin = new int[n];
			for(int i=0;i<n;i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int[] dp = new int[m + 1];
			dp[0] = 1;

			for (int i = 0; i < n; i++) { // 각 동전에 대해서
				for (int j = coin[i]; j <= m; j++) { // 만들 수 있는 각 수 ( 선택한 동전보다 큰 수들 )
					dp[j] += dp[j - coin[i]];
				}
			}
			
			sb.append(dp[m]).append("\n");
		}
		
		System.out.print(sb);
	
	}
}

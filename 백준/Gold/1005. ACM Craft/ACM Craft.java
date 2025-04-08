import java.io.*;
import java.util.*;

public class Main {
	static int n,k,start;
	static int cost[];
	static List<Integer>[] graph;
	static int[] dp;
	
	static int max;
	static int dfs(int idx) {
		if (dp[idx] != -1) return dp[idx];
		
		int result = 0;
		
		for (int next : graph[idx]) {
	        result = Math.max(result, dfs(next));
	    }
		
		dp[idx] = result + cost[idx];
		
		return dp[idx];
	}
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= t;tc++) {
        	max = Integer.MIN_VALUE;
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	k = Integer.parseInt(st.nextToken());
        	

        	dp = new int[n];
        	Arrays.fill(dp,-1);
        	
        	graph = new List[n];
        	for(int i=0;i<n;i++) {
        		graph[i] = new ArrayList<Integer>();
        	}
        	
        	cost = new int[n];
        	st = new StringTokenizer(br.readLine());
        	for(int i=0;i<n;i++) {
        		cost[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	for(int i=0;i<k;i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken())-1;
        		int d = Integer.parseInt(st.nextToken())-1;
        		
        		graph[d].add(s);
        	}
        	
        	start = Integer.parseInt(br.readLine())-1;
        	
        	System.out.println(dfs(start));
        }

    
    }
}

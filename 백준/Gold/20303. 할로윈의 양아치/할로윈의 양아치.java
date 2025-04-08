import java.io.*;
import java.util.*;

public class Main {
	static int n,m,k;
	static int[] parent;
	static int[] candy;
	static int[] groupSize;
    static int[] groupCandy;
    
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
	
	static int max = Integer.MIN_VALUE;
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    
        parent = new int[n];
        for(int i=0;i<n;i++) {
        	parent[i] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        candy = new int[n];
        for(int i=0;i<n;i++) {
        	candy[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken())-1;
        	int d = Integer.parseInt(st.nextToken())-1;
        	union(s,d);
        }
        
        for (int i = 0; i < n; i++) {
            find(i);
        }//모든 요소가 parent로 집합의 대표자 저장

        
        groupSize = new int[n];
        groupCandy = new int[n];

        for (int i = 0; i < n; i++) {
            int root = parent[i];
            groupSize[root] += 1;
            groupCandy[root] += candy[i];
        }//각 집합 i에 대해 인원 수, candy 수 저장
        
        
//        int[] dp = new int[k];
//        for (int i = 0; i < n; i++) {
//            if (groupSize[i] == 0) continue;
//            
//            for (int j = k - 1; j >= groupSize[i]; j--) {
//                dp[j] = Math.max(dp[j], dp[j - groupSize[i]] + groupCandy[i]);
//            }
//        }
        
        int[][] dp = new int[n+1][k];
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < k; j++) {

        		dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);

        		if (j + groupSize[i] < k) {
                 dp[i+1][j + groupSize[i]] = Math.max(dp[i+1][j + groupSize[i]], dp[i][j] + groupCandy[i]);
        		}
        	}
        }

        
        
        System.out.println(dp[n][k-1]);

    }
}

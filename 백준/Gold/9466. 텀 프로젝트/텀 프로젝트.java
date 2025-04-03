import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] child, parent;
	static boolean[] visited;
	static boolean[] finished;
	static int sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			n = Integer.parseInt(br.readLine());
			child = new int[n];
			parent = new int[n];
			visited = new boolean[n];
			finished = new boolean[n];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				child[i] = Integer.parseInt(st.nextToken())-1;
			}
			
			sum = 0;
			
			for(int i=0;i<n;i++) {
				if(visited[i]) continue;
				dfs(i);
			}
			
			System.out.println(n-sum);
			
		}
		
    }
	
	static void dfs(int cur) {
	    visited[cur] = true;
	    int next = child[cur];
	    
	    if (!visited[next]) {
	        dfs(next);
	    } else if (!finished[next]) {
	        int node = next;
	        sum++;
	        while (node != cur) {
	            node = child[node];
	            sum++;
	        }
	    }

	    finished[cur] = true;
	}

		
	
}

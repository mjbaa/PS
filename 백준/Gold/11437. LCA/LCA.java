import java.io.*;
import java.util.*;

public class Main {
    static int n;
	static List<Integer>[] tree;
	static int[] parent;
	static int[] depth;
	static boolean[] visited;
	
	static void dfs(int idx, int dep) {
		visited[idx] = true;
		depth[idx] = dep;
		for(int child : tree[idx]) {
			if(visited[child]) continue;
			parent[child] = idx;
			dfs(child,dep+1);
		}
		
		
	}
	
	static int lca(int a, int b) {
		while (depth[a] > depth[b]) a = parent[a];
        while (depth[b] > depth[a]) b = parent[b];
        
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
	}
	
	public static void main(String[] args) throws IOException {    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        tree = new List[n+1];
        for(int i=1;i<=n;i++) {
        	tree[i] = new ArrayList<Integer>();
        }
        
        parent = new int[n+1];
        depth = new int[n+1];
        visited = new boolean[n+1];
        
        for(int i=0;i<n-1;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	tree[s].add(d);
        	tree[d].add(s);
        }
        
        dfs(1,0);
        
        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	int anc= lca(a,b);
        	sb.append(anc).append("\n");
        }
        
        System.out.println(sb);
	}
}

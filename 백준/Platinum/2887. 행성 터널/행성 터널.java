import java.util.*;
import java.io.*;
 


public class Main {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return Integer.compare(this.weight,o.weight);
		}
	}
	
	
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
	
	static int[] parent;
	static int n;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	n = Integer.parseInt(br.readLine());
    	
    	parent = new int[n];
    	for(int i=0;i<n;i++) {
    		parent[i] = i;
    	}
    	
    	int[][] stars = new int[n][4];
    	
    	
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		int z = Integer.parseInt(st.nextToken());
    		stars[i] = new int[] {i,x,y,z};
    	}
    	
    	List<Edge> edges = new ArrayList<>(3*n);
    	for(int d = 1;d<4;d++) {
    		int d1 = d;
    		Arrays.sort(stars, (a,b) -> Integer.compare(a[d1], b[d1]));
    		
    		for(int i=0;i<n-1;i++) {
    			edges.add(new Edge(stars[i][0],stars[i+1][0],stars[i+1][d] - stars[i][d]));
    		}
    	
    	}
    	
    	Collections.sort(edges);
    	
    	int cnt = 0;
    	long sum = 0;
    	for(Edge edge : edges) {
    		if(union(edge.from, edge.to)) {
    			cnt++;
    			sum += edge.weight;
    		}
    		
    		if(cnt == n-1) break;
    	}
    	
    	
    	
    	System.out.println(sum);
    	
    	
    }
    
    
}
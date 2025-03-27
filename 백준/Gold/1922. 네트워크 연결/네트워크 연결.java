import java.util.*;
import java.io.*;
 


public class Main {
	static class Edge implements Comparable<Edge>{
		int start, end,weight;
		public Edge(int start, int end, int weight) {
			this.start =start;
			this.end = end;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int n,m;
	static Edge[] edges;
	static int parents[];
	
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		
		parents[bRoot] = aRoot;
		return true;	
	}
	
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	edges = new Edge[m];
    	parents = new int[n+1];
    	
    	StringTokenizer st;
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		int w = Integer.parseInt(st.nextToken());
    		
    		edges[i] = new Edge(s,d,w);
    	}
    	
    	for(int i=1;i<=n;i++) {
    		parents[i] = i;
    	}
    	
    	Arrays.sort(edges);
    	
    	int cnt = 0, sum = 0;
    	
    	for(Edge edge : edges) {
    		if(union(edge.end, edge.start)) {
    			cnt++;
    			sum+=edge.weight;
    		}
    		
    		
    		if(cnt == n-1) break;
    		   		
    	}
    	
    	System.out.println(sum);
    	
    }
}
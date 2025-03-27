import java.util.*;
import java.io.*;
 


public class Main {

	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int distance;
		Edge(int start, int end, int distance){
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
		
		public int compareTo(Edge o) {
			return  Integer.compare(this.distance, o.distance);
		}
	}
	
	static int parents[];
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	parents = new int[n+1];
    	boolean[] isMale = new boolean[n+1];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=1;i<=n;i++) {
    		
    		if(st.nextToken().equals("M")) {
    			isMale[i] = true;
    		}else {
    			isMale[i] = false;
    		}
    	}
    	
    	List<Edge> edges = new ArrayList<>(m);
    	
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		int distance = Integer.parseInt(st.nextToken());
    		
    		if(isMale[start] != isMale[end]) {
    			edges.add(new Edge(start,end,distance));
    		}
    	}
    	
    	for(int i=1;i<=n;i++) {
    		parents[i] = i;
    	}
    	
    	Collections.sort(edges);
    	
    	int cnt = 0, sum = 0;
    	
    	for(Edge edge : edges) {
    		if(union(edge.start, edge.end)) {
    			cnt++;
    			sum += edge.distance;
    		}
    		
    		if(cnt == n-1) break;
    	}
    	
    	if(cnt ==n -1) {
    		System.out.println(sum);
    	}else {
    		System.out.println(-1);
    	}
    	
    	
    }
}
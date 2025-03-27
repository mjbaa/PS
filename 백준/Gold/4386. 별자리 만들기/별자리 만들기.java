import java.util.*;
import java.io.*;
 


public class Main {
	static int n;
	
	static class Star{
		double x;
		double y;
		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		double distance;
		Edge(int start, int end, double distance){
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
		
		public int compareTo(Edge o) {
			return  Double.compare(this.distance, o.distance) > 0.0 ? 1 : -1;
		}
	}
	
	static int[] parents;
	
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
    	
    	Star[] stars = new Star[n];
    	List<Edge> edges = new ArrayList<>(n*n);
    	parents = new int[n];
    	
    	StringTokenizer st;
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());
    		stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
    	}
    	
    	for(int i=0;i<n-1;i++) {
    		for(int j=i+1;j<n;j++) {
    			Star s1 = stars[i];
    			Star s2 = stars[j];
    			
    			double dx = Math.abs(s1.x - s2.x);
    			double dy = Math.abs(s1.y - s2.y);
    			double distance = Math.sqrt( dx * dx + dy * dy);
    			
    			edges.add(new Edge(i,j,distance));
    		}
    	}
    	
    	
    	for(int i=0;i<n;i++) {
    		parents[i] = i;
    	}
    	
    	Collections.sort(edges);
    	
    	int cnt = 0;
    	double sum = 0.0;
    	for(Edge edge : edges) {
    		if(union(edge.start, edge.end)) {
    			cnt++;
    			sum += edge.distance;
    		}
    		
    		if(cnt == n-1) break;
    	}
    	
    	int temp = (int) ( sum * 100.0);
    	
    	sum = temp / 100.0;
    	System.out.println(sum);
    	
    	
    }
}
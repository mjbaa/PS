import java.io.*;
import java.util.*;

public class Main {
	static class Edge{
		int from, to, weight;
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	
	static final int INF = Integer.MAX_VALUE;
	static int n,m,w;
	static int[] distance;
	static List<Edge> edges;
	
	static boolean bf() {

		Arrays.fill(distance, 0);
		
		for(int i=0;i<n-1;i++) {
			for(Edge edge : edges) {
				if(distance[edge.from] != INF && distance[edge.to] > distance[edge.from]  +edge.weight) {
					distance[edge.to] = distance[edge.from]  +edge.weight;
				}
			}
		}
		
		for(Edge edge : edges) {
			if(distance[edge.from] != INF && distance[edge.to] > distance[edge.from]  +edge.weight) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=t;tc++) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	
        	distance = new int[n+1];
        	
        	
        	edges = new ArrayList<>();
        	
        	for(int i=0;i<m;i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken());
        		int w = Integer.parseInt(st.nextToken());
        		edges.add(new Edge(s,d,w));
        		edges.add(new Edge(d,s,w));
        	}
        	
        	for(int i=0;i<w;i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken());
        		int w = Integer.parseInt(st.nextToken());
        		edges.add(new Edge(s,d,-1 * w));
        	}
        	

        	
        	if(bf()) {
        		sb.append("NO\n");
        	}else {
        		sb.append("YES\n");
        	}
        	
        	
        	
        	
        }
        System.out.print(sb);
	}
	
	
}

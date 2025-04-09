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
	
	static int n, m;
	static final long INF = Long.MAX_VALUE;
	static long[] distance;
	static List<Edge> edges = new ArrayList<>();
	
	static boolean bf() {
		for(int i=0;i<n-1;i++) {
			for(Edge edge : edges) {
				if(distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.weight) {
					distance[edge.to] = distance[edge.from] + edge.weight;
				}
			}
		}
		
		for(Edge edge : edges) {
			if(distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.weight) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        distance = new long[n+1];
        Arrays.fill(distance, INF);
        distance[1] = 0l;
        
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
        	
        	edges.add(new Edge(from,to,weight));
        }
    
        
        if (bf()) {
            for (int i = 2; i <= n; i++) {
                if (distance[i] == INF) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(distance[i]).append("\n");
                }
            }
        }else {
        	sb.append(-1).append("\n");
        }

        
        System.out.println(sb);
	}
	
	
}

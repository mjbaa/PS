import java.util.*;
import java.io.*;

public class Main {
	static class Edge implements Comparable<Edge>{
		int dest;
		long weight;
		
		Edge(int dest, long weight){
			this.dest = dest;
			this.weight = weight;
		}
		
		public int compareTo(Edge e) {
			return Long.compare(this.weight, e.weight);
		}
	}
	static int n,m;
	static List<Edge>[] graph;
	static Long[] dist;
	
	static void dij(int start) {
		Arrays.fill(dist,Long.MAX_VALUE);
		dist[start] = 0l;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curNode = cur.dest;
			long curWeight = cur.weight;
			
			if(curWeight > dist[curNode]) continue;
			
			for(Edge next : graph[curNode]) {
				int nextNode = next.dest;
				long newDist;
				if(curWeight <= next.weight) newDist = next.weight + 1;
				else {
//					newDist = next.weight + ((curWeight - next.weight)/m + 1)*m+1;
					newDist = next.weight + ((long) Math.ceil(((double)curWeight - next.weight) / m)) * m + 1;

				}
				
				if(newDist < dist[next.dest]) {
					dist[next.dest] = newDist;
					pq.offer(new Edge(next.dest, newDist));
				}
				
				
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	graph = new List[n+1];
    	dist = new Long[n+1];
    	
    	for(int i=1;i<=n;i++) {
    		graph[i] = new LinkedList<>();
    	}
    	
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		graph[s].add(new Edge(d,i));
    		graph[d].add(new Edge(s,i));
    	}
    	
    	dij(1);
    	
    	System.out.println(dist[n]);
    }
}

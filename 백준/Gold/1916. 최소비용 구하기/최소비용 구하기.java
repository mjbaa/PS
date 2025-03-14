import java.util.*;
import java.io.*;

public class Main {
	static class Edge implements Comparable<Edge>{
		
		int dest;
		int weight;
		Edge(int dest, int weight){
			this.dest = dest;
			this.weight = weight;
		}
		
		public int compareTo(Edge e) {
			return Integer.compare(this.weight,  e.weight);
		}
	}
	
    static int n;
    static List<Edge>[] graph;
    static int[] dist;
    	
    static void dij(int start) {
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	dist[start] = 0;
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.offer(new Edge(start,0));
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		int curNode = cur.dest;
    		int curWeight = cur.weight; 
    		
    		if(curWeight > dist[curNode]) continue;
    		
    		for(Edge next : graph[curNode]) {
    			int nextNode = next.dest;
    			int newDist = curWeight + next.weight;
    			
    			if(newDist < dist[nextNode]) {
    				dist[nextNode] = newDist;
    				pq.offer(new Edge(nextNode, newDist));
    			}
    		}
    	}
    	
    }
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	graph = new List[n+1];
    	dist = new int[n+1];
    	for(int i=1;i<=n;i++) {
    		graph[i] = new LinkedList<>();
    	}
    	
    	int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
    	for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	graph[s].add(new Edge(d,w));
        }
    	st = new StringTokenizer(br.readLine());
    	int sidx = Integer.parseInt(st.nextToken());
    	int didx = Integer.parseInt(st.nextToken());
    	dij(sidx);
        
        System.out.println(dist[didx]);
    }
}

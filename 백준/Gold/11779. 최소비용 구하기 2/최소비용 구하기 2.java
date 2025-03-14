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
    static int[] parent;
    
    static void dij(int sidx) {
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.offer(new Edge(sidx,0));
    	
    	dist[sidx] = 0;
    	parent[sidx] = -1;
    	
    	while(!pq.isEmpty()) {
    		Edge cur = pq.poll();
    		int curNode = cur.dest;
    		int curWeight = cur.weight;
    		
    		if(curWeight > dist[curNode]) continue;
    		
    		for(Edge next : graph[curNode]) {
    			int nextNode = next.dest;
    			int newDist = dist[curNode] + next.weight;
    			
    			if(newDist < dist[nextNode]) {
    				dist[nextNode] = newDist;
    				parent[nextNode] = curNode;
    				pq.offer(new Edge(nextNode, newDist));
    			}
    		}
    	}
    }
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	graph = new List[n+1];
    	for(int i=1;i<=n;i++) {
    		graph[i] = new ArrayList<>();
    	}
    	dist = new int[n+1];
    	parent = new int[n+1];
    	
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
    	
    	StringBuilder sb = new StringBuilder();
//    	System.out.println(dist[didx]);
    	
    	int cnt = 0;
    	Stack<Integer> stack = new Stack<>();
    	
    	int idx = didx;
    	while(true) {
    		if(idx == -1) {
    			break;
    		}
    		cnt++;
    		stack.push(idx);
    		
    		idx = parent[idx];
    		
    	}
    	sb.append(dist[didx]).append("\n");
    	sb.append(cnt).append("\n");
    	while(!stack.isEmpty()) {
    		sb.append(stack.pop()).append(" ");
    	}
    	
    	System.out.println(sb);
    }
}

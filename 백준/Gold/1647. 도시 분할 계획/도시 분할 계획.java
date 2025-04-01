import java.util.*;
import java.io.*;
 


public class Main {
	static class Node implements Comparable<Node>{
		int no;
		int weight;
		Node(int no, int weight){
			this.no = no;
			this.weight = weight;
		}
		
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	boolean[] visited = new boolean[n];
    	
    	List<Node> graph[] = new List[n];
    	for(int i=0;i<n;i++) {
    		graph[i]= new ArrayList<>();
    	}
    	
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken()) -1;
    		int d = Integer.parseInt(st.nextToken()) -1;
    		int w = Integer.parseInt(st.nextToken());
    		
    		graph[s].add(new Node(d,w));
    		graph[d].add(new Node(s,w));
    		
    	}
    	
    	int[] cost = new int[n];
    	Arrays.fill(cost, Integer.MAX_VALUE);
    	
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	cost[0] = 0;
    	pq.offer(new Node(0,0));

    	
    	long sum = 0;
    	int cnt = 0;
    	
    	int max = Integer.MIN_VALUE;
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		
    		if(visited[cur.no]) continue;
    		visited[cur.no] = true;
    		    
    		sum += cur.weight;
    		max = Math.max(max, cur.weight);
    		if(++cnt == n) break;
    		
    		for(Node next : graph[cur.no]) {
    			if(visited[next.no]) continue;
    			
    			if(next.weight < cost[next.no]) {
    				cost[next.no] = next.weight;
    				pq.offer(next);
    			}

    		}
    		
    	}
    	System.out.println(sum - max);
    }
    
    
}
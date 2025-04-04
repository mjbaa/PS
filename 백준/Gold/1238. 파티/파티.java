import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
    	int no,weight;
    	Node(int no, int weight){
    		this.no = no;
    		this.weight = weight;
    	}
    	
    	public int compareTo(Node o) {
    		return Integer.compare(this.weight,o.weight);
    	}
    }
    
	static int n,m,x;
    static List<Node>[] graph;
    static int[] costToX;
    static int[] costfromX;
    
    static void dijk(int start, boolean toX) {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	int[] cost = new int[n+1];
    	Arrays.fill(cost, Integer.MAX_VALUE);
    	cost[start] = 0;
    	pq.offer(new Node(start,0));
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		
    		if(toX && cur.no == x) {
    			costToX[start] = cost[x];
    			return;
    		}
    		
    		if(cur.weight > cost[cur.no]) continue;
    		
    		for(Node next : graph[cur.no]) {
    			int newDist = cost[cur.no] + next.weight;
    			if(newDist < cost[next.no]) {
    				cost[next.no] = newDist;
    				pq.offer(new Node(next.no, newDist));
    			}
    		}	
    	}
    	
    	if(!toX) {
    		for(int i=0;i<=n;i++) {
    			costfromX[i] = cost[i];
    		}
    	}
    	
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        graph = new List[n+1];
        costToX = new int[n+1];
        costfromX = new int[n+1];
        
        for(int i=1;i<=n;i++) {
        	graph[i] = new ArrayList<Node>();
        }
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	graph[s].add(new Node(d,w));
        	
        }
        
        for(int i=1;i<=n;i++) {
        	dijk(i, true);
        }
        
        dijk(x,false);
        
        int[] result = new int[n+1];
        
        int max = Integer.MIN_VALUE;
        for(int i=1;i<=n;i++) {
        	result[i] = costfromX[i] + costToX[i];
        	max = Math.max(result[i], max);
        }
        
        System.out.println(max);
        
    }
}

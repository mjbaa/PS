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
			return Integer.compare(this.weight,o.weight);
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	int n = Integer.parseInt(br.readLine());
    	
    	
    	int[] makingCost = new int[n];
    	for(int i=0;i<n;i++) {
    		makingCost[i] = Integer.parseInt(br.readLine());
    	}
    	
    	int[][] data = new int[n+1][n+1];
    	for(int i=0;i<n;i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0;j<n;j++) {
    			data[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int i=0;i<n;i++) {
    		data[n][i] = makingCost[i];
    		data[i][n] = makingCost[i];
    	}
    	
    	
    	
    	int[] cost = new int[n+1];
    	Arrays.fill(cost,Integer.MAX_VALUE);
    	cost[n] = 0;
    	
    	boolean[] visited = new boolean[n+1];
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	pq.offer(new Node(n,0));
    	
    	int cnt = 0;
    	
    	int result = 0;
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		if(visited[cur.no]) continue;
    		
    		visited[cur.no] = true;
    		result += cur.weight;
    		if(++cnt ==n+1) break;
    		
    		for(int i=0;i<=n;i++) {
//    			if(data[cur.no][i] == 0) continue;
    			if(visited[i]) continue;
    			if(cost[i] > data[cur.no][i]) {
    				cost[i] = data[cur.no][i];
    				pq.offer(new Node(i,cost[i]));
    			}
    		}
    		
    	}
    	


    	System.out.println(result);
    	
    }
    
    
}
import java.io.*;
import java.util.*;
class Solution {
    static int visited[];
    static List<Integer> graph[];
    
    public static void bfs(int start) {
    	Queue<Integer> q = new LinkedList<>();
    	q.add(start);
    	visited[start] = 1;
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();

    		for(int node : graph[cur]) {
    			if(visited[node] != 0) continue;
    			visited[node] = visited[cur] + 1;
    			q.offer(node);
    		}
    	}
    	
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc<=10;tc++) {
        	visited = new int[101];
        	graph = new List[101];
        	
        	for(int i=1;i<101;i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int start = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i=0;i<n/2;i++) {
        		int s = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken());
        		graph[s].add(d);
        	}
        	
        	bfs(start);
        	
        	int idx = 0;
        	int max = Integer.MIN_VALUE;
        	for(int i=100;i>=1;i--) {
        		if(visited[i] > max) {
        			max = visited[i];
        			idx = i;
        		}
        	}
        	
        	sb.append("#").append(tc).append(" ").append(idx).append("\n");
        
        }
        
        System.out.println(sb);
    }
}
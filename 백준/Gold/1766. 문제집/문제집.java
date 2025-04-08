import java.io.*;
import java.util.*;

public class Main {

	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        int[] indegree = new int[n+1];
        List<Integer>[] graph = new List[n+1];
        for(int i=0;i<=n;i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	graph[s].add(d);
        	indegree[d]++;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1;i<=n;i++) {
        	if(indegree[i] == 0) {
        		pq.offer(i);
        	}
        }
        
        while(!pq.isEmpty()) {
        	int cur = pq.poll();
        	sb.append(cur).append(" ");
        	
        	for(int next : graph[cur]) {
        		indegree[next]--;
        		if(indegree[next] == 0) {
        			pq.offer(next);
        		}
        	}
        }
        
        System.out.print(sb);

    
    }
}

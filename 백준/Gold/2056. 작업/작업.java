import java.io.*;
import java.util.*;

public class Main {
	static int n;	
	static int[] indegree;
	static List<Integer>[] graph;
	static int[] cost;
	static int[] result;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        indegree = new int[n+1];
        cost = new int[n+1];
        result = new int[n+1];
        graph = new List[n+1];
        
        for(int i=1;i<=n;i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i=1;i<=n;i++) {
        	st = new StringTokenizer(br.readLine());
        	cost[i] = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
        	for(int j=0;j<m;j++) {
        		int pre = Integer.parseInt(st.nextToken());
        		indegree[i]++;
        		graph[pre].add(i);
        	}
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=n;i++) {
        	if(indegree[i] == 0) dq.offer(i);
        }
        
        while(!dq.isEmpty()) {
        	int cur = dq.poll();
        	if (result[cur] == 0) result[cur] = cost[cur];
        	
        	for(int next : graph[cur]) {
        		result[next] = Math.max(result[next], result[cur] + cost[next]);
        		indegree[next]--;
        		if(indegree[next] == 0) {
        			dq.offer(next);
        		}
        	}
        }
        
        int max = 0;
        for(int i=1;i<=n;i++) {
        	max = Math.max(max, result[i]);
        }
        System.out.println(max);
        
    }
    
    
}


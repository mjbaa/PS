import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	
	static int[] indegree;
	static List<Integer>[] graph;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        indegree = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int temp = Integer.parseInt(st.nextToken());
        	
        	int first = Integer.parseInt(st.nextToken());
        	while(st.hasMoreTokens()) {
        		int next = Integer.parseInt(st.nextToken());
        		graph[first].add(next);
        		indegree[next]++;
        		
        		first = next;
        	}
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=n;i++) {
        	if(indegree[i] == 0) dq.offer(i);
        }
        
        int count = 0;
        while(!dq.isEmpty()) {
        	int cur = dq.poll();
        	sb.append(cur).append("\n");
        	count++;
        	
        	for(int next : graph[cur]) {
        		indegree[next]--;
        		if(indegree[next] == 0) {
        			dq.offer(next);
        		}
        	}
        }
        
        if (count != n) {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
	
	}
	
	
}

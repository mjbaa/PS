import java.io.*;  
import java.util.*;  
  
public class Solution {  
	static int n,m;
	static List<Integer>[] tallerList, shorterList;
	
	static int bfs(List<Integer>[] list, int start) {
	    boolean[] visited = new boolean[n];
	    Deque<Integer> dq = new ArrayDeque<>();
	    dq.offer(start);
	    visited[start] = true;
	    int count = 0;

	    while (!dq.isEmpty()) {
	        int cur = dq.poll();
	        for (int next : list[cur]) {
	            if (visited[next]) continue;
	            visited[next] = true;
	            dq.offer(next);
	            count++;
	        }
	    }
	    return count;
	}
	

    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=t;tc++) {
        	n=  Integer.parseInt(br.readLine());
        	m = Integer.parseInt(br.readLine());
        	tallerList = new List[n];
        	shorterList = new List[n];
        	
        	for(int i=0;i<n;i++) {
        		tallerList[i] = new ArrayList<>();
        		shorterList[i] = new ArrayList<>();
        	}
        	
        	for(int i=0;i<m;i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken())-1;
        		int d = Integer.parseInt(st.nextToken())-1;
        		tallerList[s].add(d);
        		shorterList[d].add(s);
        	}
        	
        	int cnt = 0;
        	for(int i=0;i<n;i++) {
        		int sum = bfs(tallerList, i) + bfs(shorterList,i);
        		if(sum == n-1) {
        			cnt++;
        		}
        	}
        	
        	sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }  
}
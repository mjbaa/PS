import java.io.*;
import java.util.*;

public class Main {
	
    static int n,m;
    
    static int move[] = new int[101];
    static boolean visited[] = new boolean[101];
    
    
    static int min = Integer.MAX_VALUE;
    static void bfs(int start) {
    	Deque<int[]> dq = new ArrayDeque<>();
    	dq.offer(new int[] {start,0});
    	visited[start] = true;
    	
    	while(!dq.isEmpty()) {
    		int[] cur = dq.poll();
    		if(cur[0] == 100) {
    			min = Math.min(min, cur[1]);
    			return;
    		}
    		
    		for(int i=1;i<=6;i++) {
    			int next = cur[0] + i;
    			if(next > 100) continue;
    			if(visited[next]) continue;
    			
    			if(move[next] != 0) {
    				visited[next] = true;
    				next = move[next];
    			}
    			
    			visited[next]  =true;
    			dq.offer(new int[] {next, cur[1]+1});
    		}
    	}
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			move[s] = d;
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			move[s] = d;
		}
		
		bfs(1);
		
		System.out.println(min);
    }    
}
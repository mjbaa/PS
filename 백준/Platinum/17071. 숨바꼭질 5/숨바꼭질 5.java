import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	
	static int min = Integer.MAX_VALUE;
	static int limit = 0;
	
	static boolean[][] visited; 

	
	static void bfs() {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {n,0});
		visited[n][0] = true;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int loc = cur[0];
			int time = cur[1];
			
			int brother = k + time * (time + 1) / 2;
			if (brother > 500000) break;
			if (visited[brother][time % 2]) {
			    System.out.println(time);
			    return;
			}
			
			int nextTime = time + 1;
	        for (int next : new int[]{loc - 1, loc + 1, loc * 2}) {
	            if (0 <= next && next <= 500000 && !visited[next][nextTime % 2]) {
	                visited[next][nextTime %2] = true;
	                dq.offer(new int[]{next, nextTime});
	            }
	        }
			
		}
		
		System.out.println(-1);
	}
	
	public static int getTimeLimit() {
	    int limit = 0;
	    while (true) {
	        int move = limit * (limit + 1) / 2;
	        if (k + move > 500000) break;
	        limit++;
	    }
	    return limit;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        int tl = getTimeLimit();
        
        visited = new boolean[500001][3];
        
        
        bfs();
    }
    
}

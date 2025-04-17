import java.io.*;
import java.util.*;

public class Main {
    static int n;
	static int[][] data;
	static boolean[][] visited;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	
	
	static boolean notRange(int x, int y) {
		return (x<0||y<0||x>=n||y>=n);
	}
	
	static boolean bfs(int s, int e) {
		visited = new boolean[n][n];
		visited[0][0] = true;
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {0,0});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int f=0;f<4;f++) {
				int nx = x+dx[f];
				int ny = y+dy[f];
				if(notRange(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(data[nx][ny] < s || data[nx][ny] > e) continue;
				
				visited[nx][ny] = true;
				dq.offer(new int[] {nx,ny});
			}
		}
		
		return visited[n-1][n-1];
	}
	
	public static void main(String[] args) throws IOException {    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        
        
        int min = Integer.MAX_VALUE;
        int max =Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		int val = Integer.parseInt(st.nextToken());
        		data[i][j] = val;
        		min = Math.min(min, val);
        		max = Math.max(max, val);
        	}
        }
        
        
        
        int start = 0;
        int end = max - min;
        
        int result = Integer.MAX_VALUE;
        while(start<=end) {
        	int mid = (start+end)/2;
        	
        	boolean check = false;
        	for(int i=min;i<=max;i++) {
        		if(i <= data[0][0] && data[0][0] <= i+mid) {
        			if(bfs(i,i+mid)) {
            			check = true;
            			break;
            		}
        		}
        		
        	}
        	
        	if(check) {
        		result = Math.min(mid, result);
        		end = mid-1;
        	}else {
        		start = mid+1;
        	}
        	
        	
        }	
        
        System.out.print(result);
        
    }
}

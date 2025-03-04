import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int n;
	static int[][] data;
	static boolean[][] visited;
	static int smax = Integer.MIN_VALUE;
	
	static int max = 1; // 농도 0일 때 구역 1개
	//-> 농도 1부터 체크
	//smax일 때 구역0개
	//농도 체크 범위 : 1~smax-1
	
	static int cnt=0;
	
	
	static void bfs(int x, int y,int score) {
		cnt++;
		visited[x][y] = true;
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.push(new int[] {x,y});

		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			for(int f=0;f<4;f++) {
				int nx = cur[0]+dx[f];
				int ny = cur[1] + dy[f];
				
				if(nx<0 || ny <0 || nx >=n || ny >=n) continue;
				if(visited[nx][ny]) continue;
				if(data[nx][ny] <= score) continue;
				
				dq.offer(new int[] {nx,ny});
				visited[nx][ny] = true;

			}
		}
		
	}
	
	public static void main (String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		data = new int[n][n];
		
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
				if(smax < data[i][j]) {
					smax = data[i][j];
				}
			}
		}
		
		
		for(int cur = 1; cur < smax;cur++) {
			visited = new boolean[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(!visited[i][j] && data[i][j] > cur) {
						bfs(i,j,cur);
					}
				}
			} // cnt 나옴
			
			if(cnt > max) {
				max = cnt;
			}
			cnt = 0;
		}
		
		System.out.println(max);
		
	
	}

}

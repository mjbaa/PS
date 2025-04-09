import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
    static int[][] data;
	static int[][] result;
	static boolean[][] visited;
	static int[][] gdata;
	static int[] gSize;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int bfs(int i, int j, int gidx) {
		
		visited[i][j] = true;
		gdata[i][j] = gidx;
		
		int cnt = 0;
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {i,j});
		
		while(!dq.isEmpty()) {
			int cur[] = dq.poll();
			
			gdata[cur[0]][cur[1]] = gidx;
			cnt++;
			
			for(int f=0;f<4;f++) {
				int nx = cur[0] + dx[f];
				int ny = cur[1] + dy[f];
				if(nx < 0 || ny < 0 || nx >=n || ny >= m) continue;
				if(visited[nx][ny] || data[nx][ny] == 1) continue;
				visited[nx][ny] = true;

				dq.offer(new int[] {nx,ny});
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        data = new int[n][m];
        result = new int[n][m];
        visited = new boolean[n][m];
        gdata = new int[n][m]; // g id 저장
        gSize = new int[n*m+1];
        
        List<int[]> walls = new ArrayList<>();
        
        for(int i=0;i<n;i++) {
        	String line = br.readLine();
        	for(int j=0;j<m;j++) {
        		data[i][j] = line.charAt(j)-'0';
        		if(data[i][j] == 1) {
        			walls.add(new int[] {i,j});
        		}
        	}
        }
        
        int gidx = 1;
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		if(data[i][j] == 0 && !visited[i][j]) {
        			gSize[gidx] = bfs(i,j,gidx);
        			gidx++;
        		}
        	}
        }
        
        for(int[] wall : walls) {
        	int wx = wall[0];
        	int wy = wall[1];        	
        	Set<Integer> set = new HashSet<>();
        	for(int f=0;f<4;f++) {
        		int nx = wx + dx[f];
        		int ny = wy + dy[f];
        		if(nx < 0 || ny < 0 || nx >=n || ny >= m) continue;
        		if(gdata[nx][ny] != 0) {
        			set.add(gdata[nx][ny]);
        		}
        		
        	}
        	
        	int sum = 1;
        	for(int gid : set) {
        		sum += gSize[gid];
        	}
        	
        	result[wx][wy] = sum % 10;
        }
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		sb.append(result[i][j]);
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
        
    }
}

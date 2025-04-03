import java.util.*;
import java.io.*;

public class Main {
	static int[] dx= {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
    static int[][] data;
    static List<int[]> ptrs = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];
        
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<m;j++) {
        		data[i][j] = Integer.parseInt(st.nextToken());
        		if(data[i][j] != 0) ptrs.add(new int[] {i,j,0}); 
        	}
        }
        
        int years = 0;
        while(true) {
        	years++;
        	melting();
        	if(ptrs.size()==0) {
        		System.out.println(0);
        		return;
        	}else {
        		int[] ptr = ptrs.get(0);
        		int cnt = bfs(ptr[0],ptr[1]);
        		if(cnt != ptrs.size()) {
        			System.out.println(years);
        			return;
        		}
        	}
        }
        
//        melting();
//        melting();
//        for(int i=0;i<n;i++) {
//        	for(int j=0;j<m;j++) {
//        		System.out.print(data[i][j]+" ");
//        	}
//        	System.out.println();
//        }
        
        
    }
	
	static boolean notRange(int x,int y) {
		if(x < 0 || y<0 || x >=n || y >= m) return true;
		else return false;
	}
	static void melting() {
		
		for(int idx = 0;idx<ptrs.size();idx++) {
			int[] ptr = ptrs.get(idx);
			int x = ptr[0];
			int y = ptr[1];
			
			int cnt = 0;
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(notRange(nx,ny)) continue;
				if(data[nx][ny] == 0) cnt++;
			}
			
			ptr[2] = cnt;
			
		}
		
		//제거
		List<int[]> temp = new ArrayList<>();
		for(int i=0;i<ptrs.size();i++) {
			int[] ptr = ptrs.get(i);
			int x = ptr[0];
			int y = ptr[1];
			int w = ptr[2];
			data[x][y] -= w;
			ptr[2] = 0;
			if(data[x][y] > 0) {
				temp.add(new int[] {x,y,0});
			}else {
				data[x][y] = 0;
			}
			
			
		}
		ptrs = temp;
	}
	
	static int bfs(int x,int y) {
		boolean[][] visited = new boolean[n][m];
		visited[x][y] = true;
		int cnt = 1;
		
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] {x,y});
		
		while(!deq.isEmpty()) {
			int[] cur = deq.poll();
			
			for(int i=0;i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if(notRange(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(data[nx][ny] <= 0) continue;
				 
				visited[nx][ny] = true;
				
				deq.offer(new int[] {nx,ny});
				cnt++;
			}
		}
		
		return cnt;
		
		
	}
}

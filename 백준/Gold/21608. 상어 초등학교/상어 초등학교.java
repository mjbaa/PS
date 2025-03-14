import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] data;
	static int[][] like;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static boolean notRange(int x,int y) {
		return (x<1 || y<1||x>n||y>n);
	}
	
	static void find(int start) {//like 많은 순, 빈 칸 많은 순, 행 작은 순, 열 작은 순
		int maxf = -1;
		int maxn = -1;
		
		int rx = 0;
		int ry = 0;
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(data[i][j] != 0) continue;
				int fcount = 0;
				int ncount = 0;
				
				for(int f=0;f<4;f++) {//각 칸에 대해
					int nx = i+dx[f];
					int ny = j+dy[f];
					if(notRange(nx,ny)) continue;
					
					if(data[nx][ny] == 0) {
						ncount++;
						continue;
					}
					
					for(int d=0;d<4;d++) {
						if(data[nx][ny] == like[start][d]) {
							fcount++;
							break;
						}
					}
				}
				if(fcount > maxf) {
					maxf = fcount;
					maxn = ncount;
					rx = i;
					ry = j;
				}else if(fcount == maxf) {
					if(ncount > maxn) {
						maxf = fcount;
						maxn = ncount;
						rx = i;
						ry = j;
					}else if(ncount == maxn) {
						
					}
				}
				
				
			}
		}
		data[rx][ry] = start;

	}
	
	static int cal(int x,int y) {
		int sum = 0;
		
		for(int f=0;f<4;f++) {
			int nx = x+dx[f];
			int ny = y+dy[f];
			if(notRange(nx,ny)) continue;
			
			for(int i=0;i<4;i++) {
				if(data[nx][ny] == like[data[x][y]][i]) {
					sum++;
					break;
				}
			}
		}
		if(sum==0 || sum==1) {
			return sum;
		}else if(sum==2) {
			return 10;
		}else if(sum==3) {
			return 100;
		}else {
			return 1000;
		}

		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		data = new int[n+1][n+1];
		like = new int[n*n+1][4];
		
		List<Integer> list = new ArrayList<>(n*n);
		
		StringTokenizer st;
		for(int i=0;i<n*n;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			list.add(s);
			for(int j=0;j<4;j++) {
				int t = Integer.parseInt(st.nextToken());
				like[s][j] = t;
			}
		}
		for(int i=0;i<n*n;i++) {
			int idx = list.get(i);
			find(idx);
			
		}
		
		int sum = 0;
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				sum += cal(i,j);
			}
			
		}
		System.out.println(sum);

	}
}

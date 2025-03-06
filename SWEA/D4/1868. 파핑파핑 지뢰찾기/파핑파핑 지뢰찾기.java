

import java.util.*;
import java.io.*;


public class Solution{
	static int[] dx = {0,0,1,-1,1,-1,1,-1};
	static int[] dy = {1,-1,0,0,1,-1,-1,1};
	
	static int n;
	
	static char[][] data;
	static int[][] map;
	
	
	static boolean inRange(int x, int y) {
		return(0<=x && 0<=y && x<n && y<n);
	}
	
	static boolean nearSafe(int x, int y) {
		for(int i=0;i<8;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(inRange(nx,ny)) {
				if(data[nx][ny] == '*') return false;
			}
		}
		
		return true;
		
	}
	

	
	static void click(int x, int y) {
	    Queue<int[]> queue = new LinkedList<>();
	    queue.offer(new int[]{x, y});
	    map[x][y] = 0;

	    while (!queue.isEmpty()) {
	        int[] curr = queue.poll();
	        int cx = curr[0], cy = curr[1];
	        int cnt = 0;

	        for (int i = 0; i < 8; i++) {
	            int nx = cx + dx[i];
	            int ny = cy + dy[i];

	            if (inRange(nx, ny) && data[nx][ny] == '*') {
	                cnt++;
	            }
	        }

	        map[cx][cy] = cnt;
	        if (cnt == 0) {
	            for (int i = 0; i < 8; i++) {
	                int nx = cx + dx[i];
	                int ny = cy + dy[i];

	                if (inRange(nx, ny) && map[nx][ny] == -1) {
	                    map[nx][ny] = 0; // 방문 체크
	                    queue.offer(new int[]{nx, ny});
	                }
	            }
	        }
	    }
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=t; tc++) {
			n = Integer.parseInt(br.readLine());
			data = new char[n][n];
			map = new int[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j] = -1;
				}
			}
			
			for(int i=0;i<n;i++) {
				String line = br.readLine();
				for(int j=0;j<n;j++) {
					data[i][j] = line.charAt(j);
				}
			}
			
			int totalClick = 0;
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					// 지뢰 아니고 주변에 지뢰 없고 아직 map에 기록x
					if(data[i][j] != '*' && nearSafe(i,j) && map[i][j] == -1) { 
						totalClick++;
						click(i,j);
					}
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(data[i][j] == '.' && map[i][j]==-1) totalClick++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(totalClick).append("\n");
			
			
		}
		System.out.println(sb);

	}

}

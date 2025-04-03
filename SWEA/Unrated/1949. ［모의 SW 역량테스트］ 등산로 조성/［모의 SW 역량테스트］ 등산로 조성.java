import java.io.*;
import java.util.*;
 

public class Solution {
	static int n,k;
	static int[][] data;
	static boolean[][] visited;
	static List<int[]> starts;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	
	static int maxLen;
	
	static boolean flag = true;
	
	static void dfs(int x,int y, int len) {
		visited[x][y] = true;
		int cur = data[x][y];
		
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if(visited[nx][ny]) continue;
			if(data[nx][ny] >= cur+k) continue;
			
			if(data[nx][ny] < cur) {
				dfs(nx,ny,len+1); 
			}else {
				if(flag) {
					int diff = data[nx][ny] - cur + 1;
					data[nx][ny] -= diff;
					flag = false;
					dfs(nx,ny,len+1);
					flag = true;
					data[nx][ny] += diff;
				
				}
			}
		}
		visited[x][y] = false;
		maxLen = Math.max(maxLen, len);
	}
	
	
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=t;tc++) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	k = Integer.parseInt(st.nextToken());
        	
        	maxLen = Integer.MIN_VALUE;
        	starts = new ArrayList<>();
        	data = new int[n][n];
        	
        	int max = 0;
        	for(int i=0;i<n;i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0;j<n;j++) {
        			data[i][j] = Integer.parseInt(st.nextToken());
        			if(max < data[i][j]) {
        				starts.clear();
        				max = data[i][j];
        			}
        			if(max == data[i][j]) {
        				starts.add(new int[] {i,j});
        			}
        		}
        	}
        	
//        	for(int i=0;i<n;i++) {
//        		for(int j=0;j<n;j++) {
//        			if(max == data[i][j]) starts.add(new int[] {i,j});
//        		}
//        	}
        	
        	for(int[] start : starts) {
        		flag = true;
        		visited = new boolean[n][n];
        		dfs(start[0],start[1], 1);
        	}
        	
        	sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
        }
        System.out.println(sb);
    }
 

}
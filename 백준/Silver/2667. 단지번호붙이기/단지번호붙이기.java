import java.util.*;
import java.io.*;


public class Main {
	static int[][] data;
	static boolean[][] visited;
	
	static int idx = -1;
	static int[] result;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		result[idx]++;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0 && nx < data.length && ny >=0 && ny < data.length) {
				if(!visited[nx][ny] && data[nx][ny] == 1) {
					dfs(nx,ny);
				}
			}
			
		}
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	
    	data = new int[n][n];
    	visited = new boolean[n][n];
    	result = new int[n*n];
    	
    	for(int i=0;i<n;i++) {
    		char[] line = br.readLine().toCharArray();
    		for(int j=0;j<n;j++) {
    			data[i][j] = line[j] - '0';
    		}
    	}
    	
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(data[i][j] == 1 && !visited[i][j]) {
    				idx++;
    				dfs(i,j);
    			}
    		}
    	}
    	
    	Arrays.sort(result);
        System.out.println(idx+1);
        for(int i=0;i<result.length;i++) {
        	if(result[i]==0) {
        		continue;
        	}else {
        		System.out.println(result[i]);
        	}
        }
    	
    }

}
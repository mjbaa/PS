import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[][] data;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited;
    
    static int max = Integer.MIN_VALUE;
    
    static boolean notRange(int x,int y) {
    	return (x<0 || y<0 || x>=n || y>=m);
    }
    static void dfs(int x,int y,int cnt,int sum) {
    	if(cnt >= 3) {
    		max = Math.max(max, sum);
    		return;
    	}
    	
    	for(int i=0;i<4;i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		if(notRange(nx,ny)) continue;
    		if(visited[nx][ny]) continue;
    		
    		visited[nx][ny] = true;
    		dfs(nx,ny,cnt+1,sum+data[nx][ny]);
    		visited[nx][ny] = false;
    	}
    }
    
    static int check(int i,int j) {
    	int val = data[i][j];
    	
    	List<Integer> list = new ArrayList<>(4);
    	for(int f=0;f<4;f++) {
    		int nx = i+dx[f];
    		int ny = j+dy[f];
    		if(notRange(nx,ny)) continue;
    		list.add(data[nx][ny]);
    	}
    	
    	if(list.size() <= 2) return 0;
    	if(list.size() == 4) {
    		Collections.sort(list);
    		list.remove(0);
    	}
    	
    	for(int v : list) {
    		val += v;
    	}
    	
    	return val;
    	
    }
    
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
        	}
        }
        
        visited = new boolean[n][m];
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		visited[i][j] = true;
        		dfs(i,j,0,data[i][j]);
        		visited[i][j] = false;
        		
        		max = Math.max(max, check(i,j));
        		
        	}
        }
        
        System.out.println(max);
    
    }

    
}
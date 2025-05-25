import java.io.*;
import java.util.*;

public class Main {
    static int n;
	static int[] dx = {1,0};
	static int[] dy = {0,1};
	static String[][] data;
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	

	
	static void dfs(int x, int y, int sum, String oper) {
		if(x == n-1 && y == n-1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=0;i<2;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny <0 || nx >=n || ny >= n) continue;
			
			String val = data[nx][ny];
			if(val.equals("+") || val.equals("-") || val.equals("*")) {
				dfs(nx,ny,sum, val);
			}else {
				int cur = sum;
				int next = Integer.parseInt(data[nx][ny]);
				
				if(oper.equals("+")) cur += next;
				else if(oper.equals("-")) cur -= next;
				else cur *= next;
				
				dfs(nx,ny,cur, "");
			}
		}
	}
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        data = new String[n][n];
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		data[i][j] = st.nextToken();
        	}
        }
        
        dfs(0,0,Integer.parseInt(data[0][0]),"");
        
        System.out.println(max + " " + min);
	}
}

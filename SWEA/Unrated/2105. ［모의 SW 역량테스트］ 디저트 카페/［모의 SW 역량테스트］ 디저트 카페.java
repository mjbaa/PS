
import java.io.*;
import java.util.*;

public class Solution {
	static int n, max;
    static int[][] data;

    static boolean[] desserts;

    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};
    
    static void dfs(int sx, int sy, int x, int y, int cnt, int d) {
    	if(d==2 && cnt*2 < max) return;
    	
    	if (x == sx+1 && y == sy-1 && cnt >= 4) {
            max = Math.max(max, cnt);
            return;
        }
    	

        for (int i = d; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;



            if (!desserts[data[nx][ny]]) {
            	desserts[data[nx][ny]] = true;
                dfs(sx, sy, nx, ny, cnt + 1, i);
                desserts[data[nx][ny]] = false;
            }
        }
    }
    

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            data = new int[n][n];
            for (int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++){
                	data[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = -1;

            for (int i = 0; i < n-2; i++){
                for (int j = 1; j < n-1; j++){
                	desserts = new boolean[101];
                	desserts[data[i][j]] = true; 
                	dfs(i, j, i, j, 1, 0);
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
    
    
}
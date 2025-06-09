import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] data;
    static long[][] dp;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static long totalmax = Long.MIN_VALUE;

    static long dfs(int sx,int sy){
        if(dp[sx][sy] != -1){
            return dp[sx][sy];
        }

        long curmax = 1;

        for(int i=0;i<4;i++){
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(nx<0 || ny < 0 || nx >= n || ny >= n) continue;
            if(data[nx][ny] <= data[sx][sy]) continue;

            curmax = Math.max(curmax, dfs(nx,ny) + 1);
        }


        return dp[sx][sy] = curmax;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        dp = new long[n][n];
        for(long[] row : dp) Arrays.fill(row, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] == -1) {
                    long result = dfs(i,j);
                    totalmax = Math.max(totalmax, result);
                }
            }
        }

        System.out.println(totalmax);
    }
}

import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[][] data;
    static int[][] dp;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int dfs(int sx, int sy) {
        if(dp[sx][sy] != -1) return dp[sx][sy];

        int sum = 0;

        for(int i=0;i<4;i++){
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(nx<0 || ny <0 || nx >= n || ny >= m) continue;
            if(data[nx][ny] >= data[sx][sy]) continue;

            sum += dfs(nx, ny);
        }

        return dp[sx][sy] = sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n][m];
        for(int i = 0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        dp[n-1][m-1] = 1;

        data = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0,0);

        System.out.println(result);


    }
}
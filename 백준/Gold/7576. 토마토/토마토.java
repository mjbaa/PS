import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] data;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        Queue<int[]> q = new ArrayDeque<>();

        int maxCnt = 0;
        int cnt = 0;
        int days = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int val = Integer.parseInt(st.nextToken());
                data[i][j] = val;

                if (val != -1) maxCnt++;
                if (val == 1) {
                    cnt++;
                    q.offer(new int[]{i, j, 0});
                }
            }
        }

        if (cnt == maxCnt) {
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            days = Math.max(days, d);

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (data[nx][ny] != 0) continue;

                data[nx][ny] = 1;
                cnt++;
                q.offer(new int[]{nx, ny, d + 1});
            }
        }

        if (cnt == maxCnt) System.out.println(days);
        else System.out.println(-1);
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] data;
    static int[][] dist;
    static int[][] owner;
    static boolean[][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static boolean notRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    static void label(int sx, int sy, int idx) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx, sy});
        data[sx][sy] = idx;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (notRange(nx, ny)) continue;
                if (data[nx][ny] == 1) {
                    data[nx][ny] = idx;
                    dq.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        data = new int[n][n];
        dist = new int[n][n];
        owner = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (data[i][j] == 1) {
                    label(i, j, idx++);
                }
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (data[i][j] > 1) {
                    dq.offer(new int[]{i, j});
                    visited[i][j] = true;
                    owner[i][j] = data[i][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (notRange(nx, ny)) continue;

                // 바다로 확장
                if (!visited[nx][ny] && data[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    owner[nx][ny] = owner[x][y];
                    dist[nx][ny] = dist[x][y] + 1;
                    dq.offer(new int[]{nx, ny});
                }
                // 다른 섬과 만남
                else if (visited[nx][ny] && owner[nx][ny] != owner[x][y]) {
                    answer = Math.min(answer, dist[x][y] + dist[nx][ny]);
                }
            }
        }

        System.out.println(answer);
    }
}

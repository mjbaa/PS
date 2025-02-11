import java.util.*;
import java.io.*;

public class Main {
    static int[][] data;
    static int[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int bfs(int n, int m, int k) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0, 1}); // (x, y, 벽 부순 개수, 이동 거리)
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int w = cur[2];
            int dist = cur[3];
            boolean isDay = (dist % 2 == 1); // 홀수면 낮, 짝수면 밤

            if (x == n - 1 && y == m - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (data[nx][ny] == 0 && visited[nx][ny][w] == 0) { // 벽이 아닌 경우
                    visited[nx][ny][w] = 1;
                    q.add(new int[]{nx, ny, w, dist + 1});
                } else if (data[nx][ny] == 1 && w < k) { // 벽인데 부술 수 있는 경우
                    if (isDay && visited[nx][ny][w + 1] == 0) { // 낮에만 부순다
                        visited[nx][ny][w + 1] = 1;
                        q.add(new int[]{nx, ny, w + 1, dist + 1});
                    } else if (!isDay) { // 밤에는 기다린다
                        q.add(new int[]{x, y, w, dist + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        visited = new int[n][m][k + 1];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                data[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(n, m, k));
    }
}

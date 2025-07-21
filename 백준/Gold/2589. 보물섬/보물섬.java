import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] data;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int bfs(int sx, int sy) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (visited[nx][ny]) continue;
                    if (!data[nx][ny]) continue;

                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
            if (!queue.isEmpty()) dist++;
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                data[i][j] = (line.charAt(j) == 'L');
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (data[i][j]) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }

        System.out.println(max);
    }
}

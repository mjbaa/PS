import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] data;
    static boolean[][] visited;
    static List<int[]> virus = new ArrayList<>();
    static List<int[]> empty = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int max = 0;
    
    static void buildWall(int start, int depth) {
        if (depth == 3) {
            simulate();
            return;
        }

        for (int i = start; i < empty.size(); i++) {
            int[] pos = empty.get(i);
            data[pos[0]][pos[1]] = 1;
            buildWall(i + 1, depth + 1);
            data[pos[0]][pos[1]] = 0;
        }
    }

    static void simulate() {
        visited = new boolean[n][m];

        for (int[] v : virus) {
            bfs(v[0], v[1]);
        }

        int safeZone = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (data[x][y] == 0 && !visited[x][y]) {
                    safeZone++;
                }
            }
        }

        max = Math.max(max, safeZone);
    }

    static void bfs(int sx, int sy) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (data[nx][ny] != 0) continue;

                visited[nx][ny] = true;
                dq.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int val = Integer.parseInt(st.nextToken());
                data[i][j] = val;

                if (val == 2) {
                    virus.add(new int[]{i, j});
                }

                if (val == 0) {
                    empty.add(new int[]{i, j});
                }
            }
        }

        buildWall(0, 0);

        System.out.println(max);
    }

    
}

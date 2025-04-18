import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] data;
    static boolean[][] air; // 외부 공기 표시
    static List<int[]> cheeses;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void markAir() {
        air = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        air[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (data[nx][ny]) continue;

                visited[nx][ny] = true;
                air[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    static void melt() {
        List<int[]> remains = new ArrayList<>();
        List<int[]> melted = new ArrayList<>();

        for (int[] cheese : cheeses) {
            int x = cheese[0];
            int y = cheese[1];

            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (air[nx][ny]) cnt++;
            }

            if (cnt >= 2) {
                melted.add(cheese);
            } else {
                remains.add(new int[]{x, y});
            }
        }

        for (int[] cheese : melted) {
            data[cheese[0]][cheese[1]] = false;
        }

        cheeses = remains;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new boolean[n][m];
        cheeses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    data[i][j] = true;
                    cheeses.add(new int[]{i, j});
                }
            }
        }

        int time = 0;
        while (!cheeses.isEmpty()) {
            markAir();
            melt();
            time++;
        }

        System.out.println(time);
    }
}

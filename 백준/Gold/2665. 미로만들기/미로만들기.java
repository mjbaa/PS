import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] data;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                data[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE; // 초기화
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                int cost = dist[x][y] + (data[nx][ny] == 0 ? 1 : 0);
                if (cost < dist[nx][ny]) {
                    dist[nx][ny] = cost;
                    if (data[nx][ny] == 1) {
                        dq.addFirst(new int[]{nx, ny});
                    } else {
                        dq.addLast(new int[]{nx, ny});

                    }
                }
            }
        }

        System.out.println(dist[n - 1][n - 1]);
    }
}

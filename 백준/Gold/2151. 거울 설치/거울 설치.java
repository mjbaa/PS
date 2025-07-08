import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] data;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int startX, startY, endX, endY;
    static int[][][] dist;

    static boolean notRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    static void bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        dist = new int[n][n][4];
        for (int[][] arr2 : dist)
            for (int[] arr1 : arr2)
                Arrays.fill(arr1, Integer.MAX_VALUE);

        for (int d = 0; d < 4; d++) {
            dist[startX][startY][d] = 0;
            dq.offer(new int[]{startX, startY, d});
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1], dir = cur[2];

            int nx = x + dx[dir], ny = y + dy[dir];
            if (!notRange(nx, ny) && data[nx][ny] != '*') {
                if (dist[nx][ny][dir] > dist[x][y][dir]) {
                    dist[nx][ny][dir] = dist[x][y][dir];
                    dq.addFirst(new int[]{nx, ny, dir});
                }
            }

            if (data[x][y] == '!') {
                for (int d = 0; d < 4; d++) {
                    if (d == dir || (d + 2) % 4 == dir) continue;
                    nx = x + dx[d]; ny = y + dy[d];
                    if (notRange(nx, ny) || data[nx][ny] == '*') continue;
                    if (dist[nx][ny][d] > dist[x][y][dir] + 1) {
                        dist[nx][ny][d] = dist[x][y][dir] + 1;
                        dq.addLast(new int[]{nx, ny, d});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new char[n][n];

        boolean isFirst = true;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char val = line.charAt(j);
                data[i][j] = val;
                if (val == '#') {
                    if (isFirst) {
                        isFirst = false;
                        startX = i;
                        startY = j;
                    } else {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }

        bfs();
        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[endX][endY][d]);
        }
        System.out.println(answer);
    }
}

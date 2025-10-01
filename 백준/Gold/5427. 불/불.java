import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static boolean[][] visited;
    static int[][] data;
    static Deque<int[]> fires = new ArrayDeque<>(); // (x,y)
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int result = 0;

    static boolean find(int sx, int sy) {
        if (sx == 0 || sx == h - 1 || sy == 0 || sy == w - 1) {
            result = 1;
            return true;
        }

        Deque<int[]> dq = new ArrayDeque<>(); // (x,y,dist)
        dq.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!dq.isEmpty()) {
            int fsz = fires.size();
            for (int i = 0; i < fsz; i++) {
                int[] f = fires.poll();
                int fx = f[0], fy = f[1];

                for (int d = 0; d < 4; d++) {
                    int nx = fx + dx[d];
                    int ny = fy + dy[d];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if (data[nx][ny] == 0) {
                        data[nx][ny] = 2;
                        fires.offer(new int[]{nx, ny});
                    }
                }
            }

            int psz = dq.size();
            for (int i = 0; i < psz; i++) {
                int[] cur = dq.poll();
                int x = cur[0], y = cur[1], dist = cur[2];

                if (x == 0 || y == 0 || x == h - 1 || y == w - 1) {
                    result = dist + 1;
                    return true;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if (data[nx][ny] == 1 || data[nx][ny] == 2) continue;
                    if (visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    dq.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }
        return false;
    }

    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            visited = new boolean[h][w];
            data = new int[h][w];
            fires.clear();
            result = 0;

            int startX = 0, startY = 0;

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    char val = line.charAt(j);
                    if (val == '.') {
                        data[i][j] = 0;
                    } else if (val == '#') {
                        data[i][j] = 1;
                    } else if (val == '*') {
                        data[i][j] = 2;
                        fires.add(new int[]{i, j});
                    } else if (val == '@') {
                        data[i][j] = 0;
                        startX = i;
                        startY = j;
                    }
                }
            }

            if (find(startX, startY)) {
                sb.append(result).append("\n");
            } else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }

        System.out.println(sb);
    }
}

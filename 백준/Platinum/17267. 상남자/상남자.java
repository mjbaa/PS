import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int l, r;
    static int[][] data;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0}; // 아래, 위, 오른쪽, 왼쪽
    static int[] dy = {0, 0, 1, -1};

    static boolean notRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
    
    static void bfs(int startX, int startY) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startX, startY, 0, 0}); // {x, y, 왼쪽 횟수, 오른쪽 횟수}
        visited[startX][startY] = true;
        int sum = 1; // 시작점 포함

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int lcnt = cur[2];
            int rcnt = cur[3];

            // 위, 아래는 자유롭게 이동
            for (int i = 0; i < 2; i++) { // 위(1), 아래(0)
                int nx = x + dx[i];
                int ny = y + dy[i];

                while (true) {
                    if (notRange(nx, ny) || visited[nx][ny] || data[nx][ny] == 1) break;
                    visited[nx][ny] = true;
                    q.addFirst(new int[]{nx, ny, lcnt, rcnt}); // 위/아래 자유롭게 우선 탐색
                    sum++;
                    nx += dx[i];
                    ny += dy[i];
                }
            }

            // 왼쪽 이동
            if (lcnt < l) {
                int nx = x + dx[3]; // 왼쪽
                int ny = y + dy[3];
                if (!notRange(nx, ny) && !visited[nx][ny] && data[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    q.addLast(new int[]{nx, ny, lcnt + 1, rcnt});
                    sum++;
                }
            }

            // 오른쪽 이동
            if (rcnt < r) {
                int nx = x + dx[2]; // 오른쪽
                int ny = y + dy[2];
                if (!notRange(nx, ny) && !visited[nx][ny] && data[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    q.addLast(new int[]{nx, ny, lcnt, rcnt + 1});
                    sum++;
                }
            }
        }

        System.out.println(sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        visited = new boolean[n][m];

        int startX = 0, startY = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                int val = line.charAt(j) - '0';
                if (val == 1) {
                    data[i][j] = 1; // 벽
                } else {
                    data[i][j] = 0; // 통과 가능
                }
                if (val == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY);
    }
}

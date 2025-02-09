import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] data;
    static int n;

    // 현재 상어 상태
    static int x, y;
    static int size = 2;
    static int cur_eat = 0;

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];

        q.add(new int[]{x, y});
        visited[x][y] = true;

        int minDist = Integer.MAX_VALUE;
        int minX = -1, minY = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0], curY = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue; // 범위 체크
                if (visited[nx][ny] || data[nx][ny] > size) continue; // 방문 체크 및 이동 가능 여부

                visited[nx][ny] = true;
                dist[nx][ny] = dist[curX][curY] + 1;

                if (data[nx][ny] > 0 && data[nx][ny] < size) { // 먹을 수 있는 물고기 발견
                    if (dist[nx][ny] < minDist) {
                        minDist = dist[nx][ny];
                        minX = nx;
                        minY = ny;
                    } else if (dist[nx][ny] == minDist) { // 거리가 같으면, 위쪽 → 왼쪽 우선
                        if (nx < minX || (nx == minX && ny < minY)) {
                            minX = nx;
                            minY = ny;
                        }
                    }
                }
                q.add(new int[]{nx, ny});
            }
        }

        if (minX == -1) return -1; // 먹을 물고기가 없는 경우 종료
        x = minX;
        y = minY;
        data[x][y] = 0; // 물고기 먹음
        return minDist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        data = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if (data[i][j] == 9) {
                    x = i;
                    y = j;
                    data[i][j] = 0; // 상어 위치 초기화
                }
            }
        }

        int time = 0;
        while (true) {
            int dist = bfs();
            if (dist == -1) break;
            time += dist;

            cur_eat++;
            if (cur_eat == size) {
                cur_eat = 0;
                size++;
            }
        }
        System.out.println(time);
    }


}

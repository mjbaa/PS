import java.io.*;
import java.util.*;

class Solution {
    int n = 102, m = 102;
    boolean[][] isRoad = new boolean[n][m];
    boolean[][] visited = new boolean[n][m];
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rec : rectangle) {
            int x1 = rec[0] * 2;
            int y1 = rec[1] * 2;
            int x2 = rec[2] * 2;
            int y2 = rec[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    isRoad[x][y] = true;
                }
            }
        }

        for (int[] rec : rectangle) {
            int x1 = rec[0] * 2;
            int y1 = rec[1] * 2;
            int x2 = rec[2] * 2;
            int y2 = rec[3] * 2;

            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    isRoad[x][y] = false;
                }
            }
        }

        int startX = characterX * 2;
        int startY = characterY * 2;
        int targetX = itemX * 2;
        int targetY = itemY * 2;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (x == targetX && y == targetY) {
                return dist / 2;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (!isRoad[nx][ny]) continue;

                visited[nx][ny] = true;
                dq.offer(new int[]{nx, ny, dist + 1});
            }
        }

        return -1;
    }
}

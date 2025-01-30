import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int count = 0;
    static int max = 0;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static int dfs(int x, int y, int n, int m, int[][] data) {
        visited[x][y] = true;
        int size = 1;  // 현재 위치 포함 크기

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (!visited[nx][ny] && data[nx][ny] == 1) {
                    size += dfs(nx, ny, n, m, data);  // 재귀적으로 탐색된 크기 추가
                }
            }
        }
        return size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] data = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && data[i][j] == 1) {
                    count++;
                    max = Math.max(max, dfs(i, j, n, m, data));  // 그림 크기 갱신
                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }
}

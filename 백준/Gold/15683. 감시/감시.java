import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] data;
    static List<int[]> cameras = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    static int count(int[][] arr) {
        int cnt = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (arr[i][j] == 0) cnt++;
        return cnt;
    }

    static boolean notRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }

    static void check(int x, int y, int[] dir, int[][] arr) {
        for (int didx : dir) {
            int nx = x + dx[didx];
            int ny = y + dy[didx];
            while (!notRange(nx, ny) && arr[nx][ny] != 6) {
                if (arr[nx][ny] == 0) arr[nx][ny] = -1;
                nx += dx[didx];
                ny += dy[didx];
            }
        }
    }

    static int[][] copy(int[][] arr) {
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++)
            temp[i] = arr[i].clone();
        return temp;
    }

    static void dfs(int idx, int[][] arr) {
        if (idx == cameras.size()) {
            min = Math.min(min, count(arr));
            return;
        }
        int[] cur = cameras.get(idx);
        int val = data[cur[0]][cur[1]];

        List<int[]> dirs = new ArrayList<>();
        if (val == 1) {
            for (int i = 0; i < 4; i++) dirs.add(new int[]{i});
        } else if (val == 2) {
            dirs.add(new int[]{0, 1});
            dirs.add(new int[]{2, 3});
        } else if (val == 3) {
            dirs.add(new int[]{0, 3});
            dirs.add(new int[]{3, 1});
            dirs.add(new int[]{1, 2});
            dirs.add(new int[]{2, 0});
        } else if (val == 4) {
            dirs.add(new int[]{0, 1, 3});
            dirs.add(new int[]{0, 1, 2});
            dirs.add(new int[]{1, 2, 3});
            dirs.add(new int[]{0, 2, 3});
        } else if (val == 5) {
            dirs.add(new int[]{0, 1, 2, 3});
        }

        for (int[] dir : dirs) {
            int[][] next = copy(arr);
            check(cur[0], cur[1], dir, next);
            dfs(idx + 1, next);
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
                if (val != 0 && val != 6) {
                    cameras.add(new int[]{i, j});
                }
            }
        }

        dfs(0, data);
        System.out.println(min);
    }
}

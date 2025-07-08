import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] data;
    static int[][] wdata;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int startX, startY;
    static int endX, endY;
    static List<int[]> waters = new ArrayList<>();

    static final int INF = 1_000_000_000;

    static boolean notRange(int i, int j) {
        return (i < 0 || j < 0 || i >= r || j >= c);
    }

    static void wbfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.addAll(waters);

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            for (int f = 0; f < 4; f++) {
                int nx = x + dx[f];
                int ny = y + dy[f];
                if (notRange(nx, ny)) continue;
                if (wdata[nx][ny] != INF) continue;
                if (data[nx][ny] == 'X' || data[nx][ny] == 'D') continue;

                wdata[nx][ny] = time + 1;
                pq.add(new int[]{nx, ny, time + 1});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        data = new char[r][c];
        wdata = new int[r][c];

        for (int i = 0; i < r; i++) {
            Arrays.fill(wdata[i], INF);
        }

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                char value = line.charAt(j);
                data[i][j] = value;

                if (value == 'S') {
                    startX = i;
                    startY = j;
                } else if (value == 'D') {
                    endX = i;
                    endY = j;
                } else if (value == '*') {
                    waters.add(new int[]{i, j, 0});
                    wdata[i][j] = 0;
                }
            }
        }

        wbfs();

        int ftime = -1;
        boolean[][] visited = new boolean[r][c];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];

            if (x == endX && y == endY) {
                ftime = t;
                break;
            }

            for (int f = 0; f < 4; f++) {
                int nx = x + dx[f];
                int ny = y + dy[f];
                int nt = t + 1;

                if (notRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (data[nx][ny] == 'X') continue;
                if (nt >= wdata[nx][ny]) continue;

                visited[nx][ny] = true;
                dq.offer(new int[]{nx, ny, nt});
            }
        }

        if (ftime == -1) System.out.println("KAKTUS");
        else System.out.println(ftime);
    }
}

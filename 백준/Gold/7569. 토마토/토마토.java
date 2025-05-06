import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0,0,0,0,1,-1};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {1,-1,0,0,0,0};

    static int m, n, h;
    static int[][][] data;
    static boolean[][][] visited;
    static Deque<int[]> tomato = new ArrayDeque<>();

    static int totalCnt = 0;
    static int curCnt = 0;

    static boolean notRange(int x, int y, int z){
        return (x < 0 || y < 0 || z < 0 || x >= n || y >= m || z >= h);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        data = new int[n][m][h];
        visited = new boolean[n][m][h];

        for (int z = 0; z < h; z++) {
            for (int x = 0; x < n; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < m; y++) {
                    data[x][y][z] = Integer.parseInt(st.nextToken());
                    if (data[x][y][z] == 1) {
                        tomato.offer(new int[]{x, y, z});
                        visited[x][y][z] = true;
                        curCnt++;
                    }
                    if (data[x][y][z] != -1) {
                        totalCnt++;
                    }
                }
            }
        }

        int days = -1;
        while (!tomato.isEmpty()) {
            int size = tomato.size();
            days++;

            for (int i = 0; i < size; i++) {
                int[] cur = tomato.poll();
                int x = cur[0], y = cur[1], z = cur[2];

                for (int d = 0; d < 6; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    int nz = z + dz[d];

                    if (notRange(nx, ny, nz)) continue;
                    if (visited[nx][ny][nz]) continue;
                    if (data[nx][ny][nz] != 0) continue;

                    data[nx][ny][nz] = 1;
                    visited[nx][ny][nz] = true;
                    tomato.offer(new int[]{nx, ny, nz});
                    curCnt++;
                }
            }
        }

        System.out.println(curCnt == totalCnt ? days : -1);
    }
}

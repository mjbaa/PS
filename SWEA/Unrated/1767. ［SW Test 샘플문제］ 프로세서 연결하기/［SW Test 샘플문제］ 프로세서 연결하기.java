import java.io.*;
import java.util.*;

public class Solution {



    static int n;
    static int[][] visited;
    static List<int[]> cores;

    static int sum = 0;
    static int min = Integer.MAX_VALUE;
    static int maxCore = 0;

    static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    static void dfs(int idx, int coreCnt) {
        if (coreCnt + (cores.size() - idx) < maxCore) return;

        if (idx == cores.size()) {
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                min = sum;
            } else if (coreCnt == maxCore) {
                min = Math.min(min, sum);
            }
            return;
        }

        int[] core = cores.get(idx);
        int x = core[0];
        int y = core[1];

        // 4방향 모두 탐색


        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            int len = 0;
            List<int[]> path = new ArrayList<>();

            // 해당 방향으로 전선 설치 가능한지 확인
            boolean isPossible = true;
            while (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (visited[nx][ny] != 0) {
                    isPossible = false;
                    break;
                }
                path.add(new int[]{nx, ny});
                nx += dir[0];
                ny += dir[1];
                len++;
            }

            if (isPossible && len > 0) {
                for (int[] p : path) visited[p[0]][p[1]] = 2;
                sum += len;
                dfs(idx + 1, coreCnt + 1);
                sum -= len;
                for (int[] p : path) visited[p[0]][p[1]] = 0;
            }
        }

        // 전선 연결하지 않고 넘어가기
        dfs(idx + 1, coreCnt);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            cores = new ArrayList<>(12);
            n = Integer.parseInt(br.readLine());
            visited = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) {
                        visited[i][j] = 1;
                        if (i != 0 && j != 0 && i != n - 1 && j != n - 1) {
                            cores.add(new int[]{i, j});
                        }
                    } else {
                        visited[i][j] = 0;
                    }
                }
            }

            sum = 0;
            min = Integer.MAX_VALUE;
            maxCore = 0;

            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }

        System.out.println(sb);
    }
}

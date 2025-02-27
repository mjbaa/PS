import java.io.*;
import java.util.*;
 
public class Solution {
    static int n;
    static int[][] data;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
     
    static int start;
    static int max;
     
    static int bfs(int i, int j) {
        int moveCount = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
         
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
             
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
 
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (data[nx][ny] != data[x][y] + 1) continue;
                 
                queue.offer(new int[] {nx, ny});
                moveCount++;
            }
        }
        return moveCount;
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= t; test_case++) {
            n = Integer.parseInt(br.readLine());
            data = new int[n][n];
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            start = Integer.MAX_VALUE;
            max = 0;
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int moves = bfs(i, j);
 
                    if (moves > max) {
                        max = moves;
                        start = data[i][j];
                    } else if (moves == max) {
                        start = Math.min(start, data[i][j]);
                    }
                }
            }
 
            sb.append("#").append(test_case).append(" ")
              .append(start).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
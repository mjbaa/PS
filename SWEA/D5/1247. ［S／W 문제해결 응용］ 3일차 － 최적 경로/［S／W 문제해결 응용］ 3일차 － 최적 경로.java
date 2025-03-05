import java.io.*;
import java.util.*;
 
public class Solution  {
    static int n;
    static int[][] data;
    static boolean[] visited;
    static int minDistance;
     
    static void dfs(int count, int lastIdx, int distance) {
        if (distance >= minDistance) return; // 백트래킹: 이미 최소 거리보다 크면 탐색 중단
 
        if (count == n) { // 모든 고객을 방문한 경우
            distance += getDistance(lastIdx, n + 1); // 집까지 거리 추가
            minDistance = Math.min(minDistance, distance);
            return;
        }
 
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(count + 1, i, distance + getDistance(lastIdx, i));
                visited[i] = false;
            }
        }
    }
     
    static int getDistance(int idx1, int idx2) {
        return Math.abs(data[idx1][0] - data[idx2][0]) + Math.abs(data[idx1][1] - data[idx2][1]);
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            data = new int[n + 2][2];
            visited = new boolean[n + 1]; 
            minDistance = Integer.MAX_VALUE;
 
            st = new StringTokenizer(br.readLine());
            data[0][0] = Integer.parseInt(st.nextToken()); // 회사
            data[0][1] = Integer.parseInt(st.nextToken());
            data[n + 1][0] = Integer.parseInt(st.nextToken()); // 집
            data[n + 1][1] = Integer.parseInt(st.nextToken());
 
            for (int i = 1; i <= n; i++) {
                data[i][0] = Integer.parseInt(st.nextToken());
                data[i][1] = Integer.parseInt(st.nextToken());
            }
 
            dfs(0, 0, 0); // 고객 0명 방문, 회사에서 출발, 거리 0
 
            sb.append("#").append(tc).append(" ").append(minDistance).append("\n");
        }
        System.out.println(sb);
    }
}
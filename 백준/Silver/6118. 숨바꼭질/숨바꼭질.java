import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(1);

        int maxDist = 0;
        int minIdx = 0;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                minIdx = i;
                count = 1;
            } else if (dist[i] == maxDist) {
                if (i < minIdx) minIdx = i;
                count++;
            }
        }

        System.out.println(minIdx + " " + maxDist + " " + count);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
        }
    }
}

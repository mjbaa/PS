import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k, x;
    static List<Integer>[] graph;
    static int[] dist;

    static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        dist[start] = 0;
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                if (dist[next] != -1) continue;
                dist[next] = dist[cur] + 1;
                q.offer(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        dist = new int[n + 1];
        Arrays.fill(dist, -1);

        bfs(x);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) sb.append(i).append('\n');
        }

        if (sb.length() == 0) System.out.println(-1);
        else System.out.print(sb);
    }


}

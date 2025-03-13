import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int[] score;
    static int minScore = Integer.MAX_VALUE; 

    static int bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1]; 

        queue.add(start);
        visited[start] = true;
        dist[start] = 0;

        int maxDist = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int friend : graph[cur]) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    dist[friend] = dist[cur] + 1;
                    queue.add(friend);
                    maxDist = Math.max(maxDist, dist[friend]);
                }
            }
        }

        return maxDist; 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        score = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (s == -1) break;

            graph[s].add(d);
            graph[d].add(s);
        }


        for (int i = 1; i <= n; i++) {
            score[i] = bfs(i);
            minScore = Math.min(minScore, score[i]);
        }


        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == minScore) {
                candidates.add(i);
            }
        }


        System.out.println(minScore + " " + candidates.size());
        for (int f : candidates) {
            System.out.print(f + " ");
        }
    }
}

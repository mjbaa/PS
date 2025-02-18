import java.util.*;
import java.io.*;

class Main {
    static int n;
    static List<List<Integer>> taller; // 자신보다 큰 학생 리스트
    static List<List<Integer>> shorter; // 자신보다 작은 학생 리스트

    static int bfs(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++; // 방문한 학생 수 증가
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        taller = new ArrayList<>();
        shorter = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            taller.add(new ArrayList<>());
            shorter.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int tall = Integer.parseInt(st.nextToken());

            taller.get(small).add(tall);
            shorter.get(tall).add(small);
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int tallCount = bfs(taller, i); // 나보다 큰 학생 수
            int shortCount = bfs(shorter, i); // 나보다 작은 학생 수

            if (tallCount + shortCount == n - 1) {
                result++;
            }
        }

        System.out.println(result);
    }
}

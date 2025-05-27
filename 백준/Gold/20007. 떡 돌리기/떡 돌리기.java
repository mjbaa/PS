import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int idx, weight;

        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[y] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(y, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.weight > dist[cur.idx]) continue;

            for (Node next : graph[cur.idx]) {
                long newDist = dist[cur.idx] + next.weight;
                if (newDist < dist[next.idx]) {
                    dist[next.idx] = newDist;
                    pq.offer(new Node(next.idx, (int)newDist));
                }
            }
        }

        List<Long> dList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == y) continue;
            if (dist[i] == Long.MAX_VALUE || dist[i] * 2 > x) {
                System.out.println(-1);
                return;
            }
            dList.add(dist[i]);
        }

        Collections.sort(dList);

        long days = 0;
        long currDist = 0;

        for (long d : dList) {
            long doubleDist = d * 2;
            if (currDist + doubleDist <= x) {
                currDist += doubleDist;
            } else {
                days++;
                currDist = doubleDist;
            }
        }

        if (currDist > 0) days++;

        System.out.println(days == 0 ? -1 : days);
    }
}

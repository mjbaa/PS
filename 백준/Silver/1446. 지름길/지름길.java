import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx; this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int d, n;
    static ArrayList<Node>[] road;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        road = new ArrayList[d + 1];
        dist = new int[d + 1];
        for (int i = 0; i <= d; i++) {
            road[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to   = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (from > d || to > d) continue;
            if (to - from <= cost) continue;

            road[from].add(new Node(to, cost));
        }

        dijks();
        System.out.println(dist[d]);
    }

    static void dijks() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int idx = node.idx;
            int cost = node.cost;

            if (cost != dist[idx]) continue;
            if (idx == d) return;

            if (idx + 1 <= d && dist[idx + 1] > cost + 1) {
                dist[idx + 1] = cost + 1;
                pq.offer(new Node(idx + 1, dist[idx + 1]));
            }

            for (Node e : road[idx]) {
                int next = e.idx;
                int ncost = cost + e.cost;
                if (ncost < dist[next]) {
                    dist[next] = ncost;
                    pq.offer(new Node(next, ncost));
                }
            }
        }
    }


}

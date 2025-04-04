import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no;
        int weight;
        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int v, e;
    static List<Node>[] graph;
    static int[] dist;

    static void dijk(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.weight > dist[cur.no]) continue;

            for (Node next : graph[cur.no]) {
                int newDist = cur.weight + next.weight;
                if (newDist < dist[next.no]) {
                    dist[next.no] = newDist;
                    pq.offer(new Node(next.no, newDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v + 1];
        dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(d, w));
        }

        dijk(start);

        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}

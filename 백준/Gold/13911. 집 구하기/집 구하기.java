import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        public Node(int no, int weight) { this.no = no; this.weight = weight; }
        public int compareTo(Node o){ return Integer.compare(this.weight, o.weight); }
    }

    static final int INF = 1_000_000_000;

    static int v, e, m, x, s, y;
    static List<Node>[] graph;
    static int[] cdist, mdist;
    static List<Integer> cafe, mac;
    static boolean[] isMac, isCafe;

    static void dijks(List<Integer> starts, int[] dist, int limit) {
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int s : starts) {
            dist[s] = 0;
            pq.offer(new Node(s, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.weight > dist[cur.no]) continue;

            for (Node next : graph[cur.no]) {
                int newDist = dist[cur.no] + next.weight;
                if (newDist > limit) continue;
                if (dist[next.no] > newDist) {
                    dist[next.no] = newDist;
                    pq.offer(new Node(next.no, newDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new List[v + 1];
        for (int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        isMac = new boolean[v + 1];
        isCafe = new boolean[v + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        mac = new ArrayList<>(m);
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int u = Integer.parseInt(st.nextToken());
            mac.add(u);
            isMac[u] = true;
        }

        mdist = new int[v + 1];
        dijks(mac, mdist, x);

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        cafe = new ArrayList<>(s);
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            int u = Integer.parseInt(st.nextToken());
            cafe.add(u);
            isCafe[u] = true;
        }

        cdist = new int[v + 1];
        dijks(cafe, cdist, y);

        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            if (isMac[i] || isCafe[i]) continue;
            if (mdist[i] == INF || cdist[i] == INF) continue;
            long sum = (long) mdist[i] + cdist[i];
            if (sum < ans) ans = sum;
        }
        System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
    }
}

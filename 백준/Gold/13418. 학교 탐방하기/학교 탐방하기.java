import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dst, uphill;

        public Edge(int src, int dst, int uphill) {
            this.src = src;
            this.dst = dst;
            this.uphill = uphill; // 0: 오르막, 1: 내리막
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.uphill, o.uphill); // 오르막(0)을 앞에
        }
    }

    static int n, m;
    static Edge[] edges;
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }

    static int kruskal(boolean minimize) {
        for (int i = 0; i <= n; i++) parent[i] = i;

        Edge[] sorted = edges.clone();
        if (minimize) {
            Arrays.sort(sorted); // 오르막 적게
        } else {
            Arrays.sort(sorted, Collections.reverseOrder()); // 오르막 많이
        }

        int uphillCount = 0;
        for (Edge e : sorted) {
            if (union(e.src, e.dst)) {
                if (e.uphill == 0) uphillCount++; // 오르막길이면 카운트
            }
        }

        return uphillCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new Edge[m + 1];
        parent = new int[n + 1];

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int uphill = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(src, dst, uphill);
        }

        int min = kruskal(true);
        int max = kruskal(false);

        System.out.println(min * min - max * max);
    }
}

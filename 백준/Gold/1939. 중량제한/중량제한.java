import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to;
        long limit;
        Edge(int from, int to, long limit) {
            this.from = from;
            this.to = to;
            this.limit = limit;
        }
        public int compareTo(Edge o) {
            return Long.compare(o.limit, this.limit); // 내림차순
        }
    }

    static int n, m;
    static int start, end;
    static int[] parent;

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long limit = Long.parseLong(st.nextToken());
            edges.add(new Edge(a, b, limit));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Collections.sort(edges); // 내림차순

        for (Edge e : edges) {
            if(union(e.from, e.to)){
                if (find(start) == find(end)) {
                    System.out.println(e.limit);
                    return;
                }
            }

        }
    }
}

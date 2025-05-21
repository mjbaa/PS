import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int n,m;
    static List<Edge>  edges = new ArrayList<>();
    static int[] parent;

    static int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        else{
            parent[bRoot] = aRoot;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            parent[i] = i;
        }

        long origin = 0;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, weight));
            origin += weight;
        }

        Collections.sort(edges);

        long sum = 0;
        int cnt = 0;
        for(Edge e : edges){
            if(union(e.from, e.to)){
                cnt++;
                sum += e.weight;
                if(cnt == n-1) break;
            }
        }
        if(cnt != n-1){
            System.out.println(-1);
        }else{
            System.out.println(origin - sum);
        }

    }
}

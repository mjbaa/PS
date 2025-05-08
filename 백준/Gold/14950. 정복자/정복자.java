import java.io.*;
import java.util.*;

public class Main {
    static int n,m,t;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int from,to,weight;
        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public int compareTo(Edge e){
            return Integer.compare(weight,e.weight);
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from,to,weight));
        }

        Collections.sort(edges);

        int cnt = 0;
        int mst = 0;
        for(Edge e : edges){
            if(union(e.from, e.to)){
                mst+=e.weight;
                cnt++;
                if(cnt >= n-1) break;
            }
        }

        for(int i=0;i<=n-2;i++){
            mst += t * i;
        }

        System.out.println(mst);
    }
}

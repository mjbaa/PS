import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int a,b,weight;

        Edge(int a, int b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            return -1 * Integer.compare(this.weight, o.weight); // 역순정렬
        }
    }
    static int n,m,s,e;
    static List<Edge> edges = new ArrayList<>();
    static int[] parent;

    static int find(int a){
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b){
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
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a,b,weight));
        }

        Collections.sort(edges);

        int min = Integer.MAX_VALUE;


        for(Edge edge : edges){
            if(union(edge.a,edge.b)){
                min = Math.min(min,edge.weight);

                if(find(s) == find(e)) {
                    System.out.println(min);
                    return;
                }
            }
        }

        System.out.println(0);

//        System.out.println(min);

    }
}
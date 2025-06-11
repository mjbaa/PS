import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int a,b,weight;
        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int n,m,k;
    static List<Edge> edges = new ArrayList<>();
    static Set<Integer> set = new HashSet<>(); // 발전소 저장
    static int[] parent;


    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        boolean aFlag = set.contains(aRoot);
        boolean bFlag = set.contains(bRoot);

        if(aFlag && bFlag) return false; // 이미 둘다 연결됨

        // 둘 중 하나라도 발전소와 연결돼 있으면 그쪽을 부모로
        if (aFlag) {
            parent[bRoot] = aRoot;
        } else {
            parent[aRoot] = bRoot;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a,b,weight));
        }

        Collections.sort(edges);

        int mst = 0;
        for(Edge e : edges){
            if(union(e.a,e.b)){
                mst += e.weight;
            }
        }

        System.out.println(mst);


    }
}
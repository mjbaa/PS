import java.util.*;
import java.io.*;

public class Main {
    static final long INF = Long.MAX_VALUE;

    static class Edge{
        int from, to, weight;
        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static int n,f,l,m;
    static long[] dist;
    static List<Edge> edges = new ArrayList<>();
    static int[] cities;


    static List<Integer>[] graph;
    static boolean[] visited;

    static boolean reachable(int from, int target) {
        if (from == target) return true;
        visited[from] = true;
        for (int next : graph[from]) {
            if (!visited[next]) {
                if (reachable(next, target)) return true;
            }
        }
        return false;
    }

    static boolean bf() {
        for(int i = 0; i < n - 1; i++) {
            for(Edge e : edges){
                if(dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight){
                    dist[e.to] = dist[e.from] + e.weight;
                }
            }
        }

        Set<Integer> cycleNodes = new HashSet<>(); // 음의 싸이클 유발하는 node
        for(Edge e : edges){
            if(dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight){
                cycleNodes.add(e.to);
            }
        }



        for(int node : cycleNodes){
            visited = new boolean[n];
            if (reachable(node, l)) return false;
        }

        return true;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n];
        Arrays.fill(dist, INF);
        dist[f] = 0;
        //출발지

        cities = new int[n];

        graph = new List[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight)); // 비용 : 양수로 저장
            graph[from].add(to); // 인접 그래프로도 저장
        }



        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            cities[i] = Integer.parseInt(st.nextToken());
        }

        for(Edge e: edges){
            e.weight -= cities[e.to]; // 이득 : 음수로 저장
        }

        dist[f] -= cities[f];

        boolean result = bf();

        if(dist[l] == INF){ // 도달 불가능
            System.out.println("gg");
        }else{ // 도달 가능 -> 최대 이득
            if(result){ // 음의 싸이클 없음
                System.out.println(-1 * dist[l]);
            }else{ // 음의 싸이클 존재 -> 무한 이득 가능
                System.out.println("Gee");
            }
        }


    }
}
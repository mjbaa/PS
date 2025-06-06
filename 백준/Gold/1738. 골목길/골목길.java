import java.util.*;
import java.io.*;

public class Main {
    static class Edge{
        int start,end,weight;
        public Edge(int start, int end, int weight){
            this.start=start;
            this.end=end;
            this.weight=weight;
        }
    }

    static int n,m;
    static long[] dist;
    static int[] parent;
    static final int INF = Integer.MAX_VALUE;
    static List<Edge> edges = new ArrayList<>();
    static List<Integer>[] graph;

    static boolean[] visited;
    static boolean connected(int start, int target){
        if(start == target) return true;
        visited[start] = true;
//        for(int n : graph[start]){
//            if(visited[n]) continue;
//            return connected(n, target);
//        }
        for(int next : graph[start]){
            if(!visited[next] && connected(next, target)) return true;
        }
        return false;
    }

    static boolean bf(){
        for(int i=0;i<n-1;i++){
            for(Edge e : edges){
                if(dist[e.start] != INF && dist[e.end] > dist[e.start] + e.weight){
                    dist[e.end]=dist[e.start] + e.weight;
                    parent[e.end] = e.start;
                }
            }
        }

        Set<Edge> circleEdges = new HashSet<>();
        for(Edge e : edges){
            if(dist[e.start] != INF && dist[e.end] > dist[e.start] + e.weight){
                circleEdges.add(e);
            }
        }

        for(Edge e : circleEdges){
            visited = new boolean[n+1];

            if(connected(e.start,n)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        parent = new int[n+1];

        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, -1 * weight)); // 최대 이득 경로 위해 이득을 음수로, 손해를 양수로 변환 -> 최소값 * -1 = 최대이득
            graph[start].add(end);
        }

        if(bf()){
            Stack<Integer> stack = new Stack<>();

            int cur = n;
            stack.push(cur);
            while( cur != 1){
                cur = parent[cur];
                stack.push(cur);
            }

            while(!stack.isEmpty()){
                sb.append(stack.pop()).append(" ");
            }
        }else{
            sb.append(-1);
        }

        System.out.println(sb);

    }
}
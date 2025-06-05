import java.util.*;
import java.io.*;

public class Main {
    static final long INF = Long.MAX_VALUE;

    static class Edge{
        int from,to,weight;
        Edge(int from,int to,int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static int n,m,w;
    static long[] dist;
    static List<Edge> edges;

    static boolean bf(){
        for(int i=0;i<n-1;i++){
            for(Edge e : edges){
                if(dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight){
                    dist[e.to] = dist[e.from] + e.weight;
                }
            }
        }

        for(Edge e : edges){
            if(dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc=0;tc<t;tc++){
            edges = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            dist = new long[n+1];
            Arrays.fill(dist,0);
//            dist[1] = 0;

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start,from,weight));
                edges.add(new Edge(from,start,weight));

            }

            for(int i=0;i<w;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = -1 * Integer.parseInt(st.nextToken());
                edges.add(new Edge(start,from,weight));
            }

            if(bf()){
                sb.append("NO\n");
            }else{
                sb.append("YES\n");
            }

        }

        System.out.println(sb);

    }
}
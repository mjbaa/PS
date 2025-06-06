import java.util.*;
import java.io.*;

public class Main {
    static class Edge{
        int start, end , weight;
        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int n,m;
    static int[][] dist;
    static int[] distMax;
    static List<Edge> edges = new ArrayList<>();
    static int INF = Integer.MAX_VALUE / 2;


    static float edgeBurnTime(int aStart, int bStart, int len) {
        int diff = Math.abs(aStart - bStart);
        if (diff >= len) {
            return Math.min(aStart, bStart) + len;
        } else {
            return Math.max(aStart, bStart) + (len - diff) / 2f;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        distMax = new int[n+1];


        dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        m = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, weight));

            dist[start][end] = Math.min(dist[start][end], weight);
            dist[end][start] = Math.min(dist[end][start], weight);



        }

        for(int k = 1; k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    int newDist = dist[i][k] + dist[k][j];
                    if(dist[i][j] > newDist){
                        dist[i][j] = newDist;
                    }
                }
            }
        }

        float answer = Float.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            float curMaxTime = 0f;
            for (Edge edge : edges) {
                float burnTime = edgeBurnTime(dist[i][edge.start], dist[i][edge.end], edge.weight);
                curMaxTime = Math.max(curMaxTime, burnTime);
            }
            answer = Math.min(answer, curMaxTime);
        }

        System.out.printf("%.1f\n", answer);
        

    }
}
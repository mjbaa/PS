import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int end,weight;
        Node(int end,int weight){
            this.end=end;
            this.weight=weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight,o.weight);
        }
    }
    static int n,m,r;
    static int[] items;
    static int[] dist;
    static int[][] data;

    static List<Node>[] graph;
    static int max = 0;

    static void dijks(int start){
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.end]){
                if(dist[next.end] > dist[cur.end] + next.weight){
                    dist[next.end] = dist[cur.end] + next.weight;

                    if(dist[next.end] <= m){
                        pq.offer(new Node(next.end, dist[next.end]));
                    }

                }
            }
        }

        int sum = 0;
        for(int i=1;i<=n;i++){
            if(dist[i] <= m) sum+=items[i];
        }

        max = Math.max(max,sum);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n+1];
        dist = new int[n+1];
        data = new int[n+1][n+1];
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end,weight));
            graph[end].add(new Node(start, weight));
        }

        for(int i=1;i<=n;i++){
            dijks(i);
        }

        System.out.println(max);

    }
}

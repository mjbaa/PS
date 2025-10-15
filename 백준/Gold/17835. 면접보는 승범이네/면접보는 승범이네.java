import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int no;
        long weight;
        Node(int no, long weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Long.compare(this.weight, o.weight);
        }
    }
    static int n,m,k;
    static long[] dist;
    static boolean[] visited;
    static List<Node>[] graph;

    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];

        dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[b].add(new Node(a, weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            int start = Integer.parseInt(st.nextToken());
            dist[start] = 0;
            pq.offer(new Node(start,0));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.no]) continue;
            visited[cur.no] = true;

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no, dist[next.no]));
                }
            }
        }

        long max = 0;
        int idx = -1;
        for(int i=1;i<=n;i++){
            if(dist[i] > max){
                max = dist[i];
                idx = i;
            }
        }

        System.out.println(idx);
        System.out.println(max);


    }

}

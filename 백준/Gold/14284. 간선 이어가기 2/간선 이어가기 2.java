import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n,m;

    static int[] dist;
    static List<Node>[] graph;

    static void dijks(int start) {
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no, dist[next.no]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,weight));
            graph[b].add(new Node(a,weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijks(start);

        System.out.println(dist[end]);


    }
}

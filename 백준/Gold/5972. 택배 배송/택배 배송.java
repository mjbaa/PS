import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int no, weight;
        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n,m;
    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, weight));
            graph[end].add(new Node(start, weight));

        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1,0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no, dist[next.no]));
                }
            }
        }

        System.out.println(dist[n]);

    }
}

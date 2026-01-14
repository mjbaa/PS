import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int v,e;
    static int[] dist;
    static List<Node>[] graph;

    static void dijks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.no] < cur.weight) continue;

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no, dist[next.no]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new List[v+1];
        for(int i=1;i<=v;i++){
            graph[i] = new ArrayList<>();
        }
        dist = new int[v+1];

        int start = Integer.parseInt(br.readLine());

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,weight));
        }

        dijks(start);
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=v;i++){
            int val = dist[i];
            if(val == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(val);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

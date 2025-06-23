import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int v,e;
    static int[] dist;
    static List<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int start = Integer.parseInt(br.readLine());

        graph = new List[v+1];
        for(int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,weight));
//            graph[b].add(new Node(a,weight));
        }

        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no,dist[next.no]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= v; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                sb.append("INF");
            }else{
                sb.append(dist[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

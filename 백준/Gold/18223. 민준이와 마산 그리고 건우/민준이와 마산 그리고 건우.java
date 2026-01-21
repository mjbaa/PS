import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int no, weight;
        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return this.weight - n.weight;
        }
    }
    static int v,e,p;
    static int[] dist;
    static List<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        dist = new int[v+1];

        graph = new List[v+1];
        for(int i=1;i<=v;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,weight));
            graph[b].add(new Node(a,weight));
        }

        dijks(1);
        int min = dist[v];

        int toMin = dist[p];
        dijks(p);
        int result = toMin + dist[v];

        if(result <= min) System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");

    }

    static void dijks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
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


}

import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int no;
        long weight;
        Node(int no, long weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return Long.compare(this.weight, n.weight);
        }
    }

    static int n,m;
    static List<Node>[] graph;
    static long[] dist;
    static boolean[] canGo;

    static void dijks(int start){
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start , 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.no]) continue;
            visited[cur.no] = true;

            for(Node next : graph[cur.no]){
                if(next.no != n-1 && !canGo[next.no]) continue;

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
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        canGo = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int val = Integer.parseInt(st.nextToken());
            if(val == 1){
                canGo[i] = false;
            }else{
                canGo[i] = true;
            }
        }

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if((canGo[a] || a == n-1) && (canGo[b] || b == n-1)){
                graph[a].add(new Node(b, weight));
                graph[b].add(new Node(a, weight));
            }
        }

        dijks(0);

        if(dist[n-1] == Long.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(dist[n-1]);

        }


    }
}
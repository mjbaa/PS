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

    static int n,m,k;
    static List<Node>[] graph;
    static PriorityQueue<Integer>[] dist;

    static void dijks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start,0));
        dist[start].offer(0);

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node next : graph[cur.no]){
                int newWeight = cur.weight + next.weight;

                if(dist[next.no].size() < k){
                    dist[next.no].offer(newWeight);
                    pq.offer(new Node(next.no, newWeight));
                }else if(dist[next.no].peek() > newWeight){
                    dist[next.no].poll();
                    dist[next.no].offer(newWeight);
                    pq.offer(new Node(next.no, newWeight));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        dist = new PriorityQueue[n+1];
        for(int i = 1; i <= n; i++){
            dist[i] = new PriorityQueue(Collections.reverseOrder());
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, weight));
        }

        dijks(1);

        for(int i=1;i<=n;i++){
            if(dist[i].size() < k){
                sb.append(-1).append("\n");
            }else{
                sb.append(dist[i].peek()).append("\n");
            }
        }
        System.out.println(sb);
    }
}

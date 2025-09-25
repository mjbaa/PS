import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        public Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] dist;
    static int[][] result;
    static List<Node>[] graph;
    static int n,m;

    static void dijks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no, dist[next.no]));

                    if(cur.no == start) result[start][next.no] = next.no;
                    else result[start][next.no] = result[start][cur.no];

                }
            }
        }
    }


    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        result = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            Arrays.fill(result[i],-1);
        }

        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,weight));
            graph[b].add(new Node(a,weight));
        }

        for(int i=1;i<=n;i++){
            Arrays.fill(dist, Integer.MAX_VALUE);
            dijks(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j) sb.append("-");
                else sb.append(result[i][j]);

                if(j != n) sb.append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }
}
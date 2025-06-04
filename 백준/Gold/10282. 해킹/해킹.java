import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int no, weight;
        Node(int no,int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight,o.weight);
        }
    }

    static int n,d,c;
    static List<Node>[] graph;
    static int[] dist;

    static void dijks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()){
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
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=t;tc++){
            st = new  StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dist = new int[n+1];
            Arrays.fill(dist,Integer.MAX_VALUE);

            graph = new List[n+1];
            for(int i=1;i<=n;i++){
                graph[i] = new ArrayList<>();
            }

            for(int i=0;i<d;i++){
                st = new  StringTokenizer(br.readLine());
                int end = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[start].add(new Node(end,weight));
            }

            dijks(c);

            int max = 0;
            int cnt = 0;
            for(int i=1;i<=n;i++){
                if(dist[i]==Integer.MAX_VALUE) continue;

                cnt++;
                max = Math.max(max,dist[i]);
            }
            sb.append(cnt).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}

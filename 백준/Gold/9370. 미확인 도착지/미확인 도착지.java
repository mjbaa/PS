import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int n,m,t,s,g,h;
    static int gtoh;

    static int[] distA;
    static int[] distB;

    static List<Node>[] graph;
    static List<Integer> targets;

    static void dijks(int start, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.no] < cur.weight) continue;

            for (Node next : graph[cur.no]) {
                if (dist[next.no] > dist[cur.no] + next.weight) {
                    dist[next.no] = dist[cur.no] + next.weight;
                    pq.offer(new Node(next.no, dist[next.no]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < TC; tc++) {
            gtoh = 0;
            sb = new StringBuilder();

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

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
                if((a == g && b == h) || (a == h && b == g)){
                    gtoh = weight;
                }
            }

            targets = new ArrayList<>();
            for(int i=0;i<t;i++){
                targets.add(Integer.parseInt(br.readLine()));
            }

            int[] distS = new int[n + 1];
            int[] distG = new int[n + 1];
            int[] distH = new int[n + 1];

            dijks(s, distS);
            dijks(g, distG);
            dijks(h, distH);

            Collections.sort(targets);


            for(int t : targets){
                if(distS[t] == distS[g] + gtoh + distH[t] ||
                    distS[t] == distS[h] + gtoh + distG[t]) {
                    sb.append(t).append(" ");
                }

            }

            System.out.println(sb);



        }
    }
}

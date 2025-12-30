import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int no, dist, state;

        Node(int no, int dist, int state) {
            this.no = no;
            this.dist = dist;
            this.state = state;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int n, m;
    static int[] foxDist;
    static int[][] wolfDist;
    static List<Node>[] graph;


    static void dijkstraFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, 0));
        foxDist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (foxDist[cur.no] < cur.dist) continue;

            for(Node next : graph[cur.no]){
                int newDist = foxDist[cur.no] + next.dist * 2;
                if(foxDist[next.no] > newDist){
                    foxDist[next.no] = newDist;
                    pq.offer(new Node(next.no,newDist,0));
                }
            }
        }
    }

    static void dijkstraWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        wolfDist[1][0] = 0;
        pq.offer(new Node(1, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (wolfDist[cur.no][cur.state] < cur.dist) continue;

            for (Node next : graph[cur.no]) {
                int nextState = 1 - cur.state;
                int newDist = cur.dist;
                int cost;

                if (cur.state == 0) { // 뜀
                    newDist += next.dist;
                } else {             // 걷기
                    newDist += next.dist * 4;
                }

                if (wolfDist[next.no][nextState] > newDist) {
                    wolfDist[next.no][nextState] = newDist;
                    pq.offer(new Node(next.no, newDist, nextState));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        foxDist = new int[n+1];
        wolfDist = new int[n+1][2];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            foxDist[i] = Integer.MAX_VALUE;
            wolfDist[i][0] = wolfDist[i][1] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, d,0));
            graph[b].add(new Node(a, d,0));
        }

        dijkstraFox();
        dijkstraWolf();

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            int wolfMin = Math.min(wolfDist[i][0], wolfDist[i][1]);
            if (wolfMin > foxDist[i]) cnt++;
        }

        System.out.println(cnt);
    }
}

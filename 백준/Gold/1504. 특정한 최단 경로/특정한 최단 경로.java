import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n, e;
    static List<Node>[] graph;
    static int[] must = new int[2];
    static int[] cost;
    
    static void dijk(int start) {
//        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        cost[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.weight > cost[cur.no]) continue;
            for (Node next : graph[cur.no]) {
                if (cost[cur.no] + next.weight < cost[next.no]) {
                    cost[next.no] = cost[cur.no] + next.weight;
                    pq.offer(new Node(next.no, cost[next.no]));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        cost = new int[n];
        
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(d, w));
            graph[d].add(new Node(s, w));
        }

        st = new StringTokenizer(br.readLine());
        must[0] = Integer.parseInt(st.nextToken()) - 1;
        must[1] = Integer.parseInt(st.nextToken()) - 1;

        // 경로 1: 0 -> must[0] -> must[1] -> n-1
//      // 경로 2: 0 -> must[1] -> must[0] -> n-1
        dijk(must[0]);
        int a = cost[0];
        int b = cost[must[1]];
        int f = cost[n-1];
        
        
        dijk(must[1]);
        int e = cost[must[0]];
        int d = cost[0];
        int c = cost[n-1];
        


        int path1 =  a + b + c;
        int path2 =  d + e + f;

        int result = Math.min(path1, path2);

        // 경로 중 하나라도 도달 불가일 경우 처리
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE ||
            d == Integer.MAX_VALUE || e == Integer.MAX_VALUE || f == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}

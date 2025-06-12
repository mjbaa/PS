import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight;
        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int m, n;
    static List<Node>[] graph;

    static int prim() {
        boolean[] visited = new boolean[m];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        int mst = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.no]) continue;
            visited[cur.no] = true;
            mst += cur.weight;
            count++;

            for (Node next : graph[cur.no]) {
                if (!visited[next.no]) {
                    pq.offer(new Node(next.no, next.weight));
                }
            }
        }

        return (count == m) ? mst : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 정점 수
            n = Integer.parseInt(st.nextToken()); // 간선 수

            if (m == 0 && n == 0) break;

            graph = new ArrayList[m];
            for (int i = 0; i < m; i++) {
                graph[i] = new ArrayList<>();
            }

            int origin = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[a].add(new Node(b, w));
                graph[b].add(new Node(a, w));
                origin += w;
            }

            int mst = prim();
            System.out.println(origin - mst);
        }
    }
}

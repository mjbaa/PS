import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no,weight;
        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n;
    static int[][] data;
    static boolean[] visited;

    static long prim(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        long sum = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.no]) continue;
            visited[cur.no] = true;
            sum += cur.weight;

            for(int i=0;i<n;i++){
                if (!visited[i]) {
                    pq.offer(new Node(i, data[cur.no][i]));
                }
            }
        }

        return sum;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long mst = prim(0);

        System.out.println(mst);
    }
}
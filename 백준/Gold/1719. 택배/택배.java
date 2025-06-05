import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int no, weight, first;
        Node(int no, int weight,int first){
            this.no = no;
            this.weight = weight;
            this.first = first;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n,m;
    static int[] dist;
    static List<Node>[] graph;
    static int[][] result;

    static void dijks(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0,0));

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no] + next.weight){
                    dist[next.no] = dist[cur.no] + next.weight;
                    if(cur.first == 0){ // 바로 두번째 도착한 노드임
                        pq.offer(new Node(next.no, dist[next.no], next.no));
//                        if(result[start][next.no]==0){
                            result[start][next.no] = next.no;
//                        }
                    }else{
                        pq.offer(new Node(next.no, dist[next.no], cur.first));
//                        if(result[start][next.no]==0){
                            result[start][next.no] = cur.first;
//                        }
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());



        graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }

        result = new int[n+1][n+1];

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(from, weight,0));
            graph[from].add(new Node(start, weight,0));

        }

        for(int i = 1; i <= n; i++){
            dijks(i);
        }

        for(int i=1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i==j){
                    sb.append("-").append(" ");
                }else{
                    sb.append(result[i][j]).append(" ");
                }

            }
            sb.append("\n");
        }

        System.out.println(sb);


    }
}
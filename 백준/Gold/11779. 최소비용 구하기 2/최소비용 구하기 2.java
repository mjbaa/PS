import java.util.*;
import java.io.*;

class Main {
    static class Node implements Comparable<Node> {
        int no;
        int weight;

        Node(int no, int weight){
            this.no = no;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n,m;
    static List<Node>[] graph;
    static int[] dist;
    static int[] parent;
    static int start,end;

    static void dijks(){
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.weight > dist[cur.no]) continue;
            
            for(Node next : graph[cur.no]){
                if(dist[next.no] > dist[cur.no]+next.weight){
                    dist[next.no] = dist[cur.no]+next.weight;
                    pq.offer(new Node(next.no,dist[next.no]));
                    parent[next.no] = cur.no;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new List[n+1];
        dist = new int[n+1];
        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,weight));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijks();

        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");

        Stack<Integer> stack = new Stack<>();
        int cur = end;
        while(cur != -1) {
            stack.push(cur);
            cur = parent[cur];
        }
        sb.append(stack.size()).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}


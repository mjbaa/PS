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

    static int n,m;
    static int[] dist;
    static List<Integer>[] parent;
    static List<Node> graph[];
    static Set<String> set;
    static int s,d;

    static void dijks(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s,0));

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.no] < cur.weight) continue;

            for(Node next : graph[cur.no]){

                if(set.contains(cur.no+","+next.no)) continue;

                int newDist = dist[cur.no] + next.weight;

                if(dist[next.no] > newDist){
                    dist[next.no] = newDist;
                    parent[next.no].clear();
                    parent[next.no].add(cur.no);
                    pq.offer(new Node(next.no, newDist));
                }else if(dist[next.no] == newDist){
                    parent[next.no].add(cur.no);
                }
            }
        }

    }

    static void bfs(){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(d);
        boolean[] visited = new boolean[n];
        visited[d] = true;

        while(!dq.isEmpty()){
            int cur = dq.poll();

            for(int p : parent[cur]){
                set.add(p+","+cur);

                if(visited[p]) continue;
                dq.offer(p);
                visited[p] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;
            if (set != null) set.clear();
            else set = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            dist = new int[n];
            parent = new List[n];
            graph = new List[n];
            for(int i=0;i<n;i++){
                parent[i] = new ArrayList<>();
                graph[i] = new ArrayList<>();
            }

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b,weight));
            }

            dijks();

            bfs();

            dijks();
            if(dist[d] == Integer.MAX_VALUE){
                sb.append(-1);
            }else{
                sb.append(dist[d]);
            }
            sb.append("\n");
        }
        System.out.println(sb);


    }

}

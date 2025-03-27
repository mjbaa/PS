import java.util.*;
import java.io.*;



public class Main {
    static int[] distance;
    static int v;
    static List<int[]> graph[];


    static void bfs(int node){
        distance = new int[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Deque<Integer> queue = new LinkedList<>();
        queue.add(node);
        distance[node] = 1;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int[] next : graph[cur]){
                int newDistance = distance[cur]+next[1];
                if(newDistance < distance[next[0]]){
                    distance[next[0]] = newDistance;
                    queue.add(next[0]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());
        graph = new List[v+1];
        for(int i=1;i<=v;i++){
            graph[i] = new ArrayList<>();
        }
        distance = new int[v+1];

        StringTokenizer st;
        for(int i=0;i<v;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while(true){
                int end = Integer.parseInt(st.nextToken());

                if(end == -1) break;

                int weight = Integer.parseInt(st.nextToken());
                graph[start].add(new int[]{end,weight});
                graph[end].add(new int[]{start,weight});

            }
        }
        bfs(1);

        int max = Integer.MIN_VALUE;
        int idx = 0;
        for(int i=1;i<=v;i++){
            if(distance[i] > max){
                max = distance[i];
                idx = i;
            }
        }

        bfs(idx);
        max = Integer.MIN_VALUE;
        for(int i=1;i<=v;i++){
            if(distance[i] > max){
                max = distance[i];
            }
        }

        System.out.println(max-1);




    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int k,e,v;
    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean[] colored;
    static boolean flag;

    static void bfs(int sidx){
        Deque<Integer> dq = new  ArrayDeque<>();
        dq.offer(sidx);
        colored[sidx] = true;
        visited[sidx] = true;

        loop : while(!dq.isEmpty()){
            int cur = dq.poll();

            for(int next : graph[cur]){
                if(visited[next]){
                    if(colored[next] == colored[cur]){
                        flag = false;
                        break loop;
                    }
                }else{
                    visited[next] = true;
                    colored[next] = !colored[cur];
                    dq.offer(next);
                }
            }
        }
    }

    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=k;tc++){
            flag = true;

            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e =  Integer.parseInt(st.nextToken());
            visited = new boolean[v+1];
            colored = new boolean[v+1];
            graph = new List[v+1];
            for(int i=1;i<=v;i++){
                graph[i] = new ArrayList<>();
            }

            for(int i=0;i<e;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            for(int i=1;i<=v;i++){
                if(!visited[i]){
                    bfs(i);
                }
            }


            if(flag){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }

        }
        System.out.println(sb);

    }

}

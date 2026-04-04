import java.util.*;
import java.io.*;

public class Main {
    static int n,e;
    static List<Integer>[] graph;
    static int[] visited;

    static boolean bfs(int start){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        visited[start] = 1;

        while(!dq.isEmpty()){
            int cur = dq.poll();
            int curColor = visited[cur];

            int nextColor = 1;
            if(curColor == 1) nextColor = 2;

            for(int next : graph[cur]){
                if(visited[next] == 0){
                    visited[next] = nextColor;
                    dq.offer(next);
                }else{
                    if(visited[next] != nextColor) return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc = 0;tc<t;tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            graph = new List[n+1];
            for(int i=1;i<=n;i++){
                graph[i] = new ArrayList<>();
            }

            visited = new int[n+1];

            for(int i=0;i<e;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            boolean flag = false;
            for(int i=1;i<=n;i++){
                if(visited[i] == 0){
                    if(!bfs(i)) {
                        sb.append("NO").append("\n");
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) continue;
            sb.append("YES");
            sb.append("\n");


        }
        System.out.println(sb);
    }


}

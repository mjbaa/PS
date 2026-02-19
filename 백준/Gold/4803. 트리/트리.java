import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static List<Integer>[] graph;
    static boolean[] visited;

    static boolean isTree(int start){
        Set<Integer> set = new HashSet<>();
        Deque<int[]> dq = new ArrayDeque<>();

        dq.offer(new int[] {start,0}); // node , parent
        set.add(start);
        visited[start] = true;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();

            for(int next : graph[cur[0]]){
                if(next != cur[1]){
                    if(set.contains(next)){
                        return false;
                    }

                    dq.offer(new int[] {next, cur[0]});
                    set.add(next);
                    visited[next] = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            if((n = Integer.parseInt(st.nextToken())) == 0) break;
            m = Integer.parseInt(st.nextToken());

            graph = new List[n+1];
            for(int i=1;i<=n;i++) {
                graph[i] = new ArrayList<>();
            }

            visited = new boolean[n+1];

            for(int i=1;i<=m;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            int cnt = 0;
            for(int i=1;i<=n;i++) {
                if(visited[i]) continue;
                if(isTree(i)) cnt++;
            }

            if(cnt == 0){
                sb.append("Case ").append(tc).append(": No trees.").append("\n");
            }else if(cnt == 1){
                sb.append("Case ").append(tc).append(": There is one tree.").append("\n");
            }else{
                sb.append("Case ").append(tc).append(": A forest of ").append(cnt).append(" trees.").append("\n");
            }

            tc++;
        }
        System.out.println(sb);
    }
}

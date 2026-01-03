import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    static int n;
    static int[] data;

    static int[] indegree;

    static List<Integer>[] graph;
    static int[] result;

    static void topo(){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(indegree[i] == 0 ) {
                dq.offer(i);
                result[i] = data[i];
            }
        }

        while(!dq.isEmpty()){
            int cur = dq.poll();


            for(int next : graph[cur]){
                indegree[next]--;
                result[next] = Math.max(result[next], result[cur] + data[next]);
//              result[next] = result[cur] + data[next];
                
                if(indegree[next] == 0){
                    dq.offer(next);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        data = new int[n+1];
        indegree = new int[n+1];
        result = new int[n+1];
        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            data[i] = time;
            while(true){
                int a = Integer.parseInt(st.nextToken());
                if(a == -1) break;

                graph[a].add(i);
                indegree[i]++;
            }
        }

        topo();

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int n,r,q;

    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] result;

    static int find(int cur){
        visited[cur] = true;

        int sum = 1;
        for(int next : graph[cur]){
            if(visited[next]) continue;

            sum += find(next);
        }
        
        return result[cur] = sum;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        result = new int[n+1];
        Arrays.fill(result, -1);
        graph = new List[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited[r] = true;
        find(r);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++){
            int target = Integer.parseInt(br.readLine());
            sb.append(result[target]).append("\n");
        }

        System.out.println(sb);
    }
}
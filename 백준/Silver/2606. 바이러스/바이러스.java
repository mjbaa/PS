import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[n][n];

        StringTokenizer st;
        while(k-- > 0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;

            graph[s][d] = true;
            graph[d][s] = true;
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(0);
        visited[0] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0;i<n;i++){
                if(graph[cur][i] && !visited[i]){
                    cnt++;
                    q.add(i);
                    visited[i] = true;
                }
            }
        }

        System.out.println(cnt);
    }
}
import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(s).add(d);
            indegree[d]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }


        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()){
            int node = q.poll();
            sb.append(node).append(" ");

            for(int dest : graph.get(node)){
                indegree[dest]--;
                if(indegree[dest]==0){
                    q.offer(dest);
                }
            }
        }



        System.out.println(sb.toString());
    }
}
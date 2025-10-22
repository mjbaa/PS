import java.util.*;

class Solution {
    int n;
    int[] dist;
    boolean[] visited;
    List<Integer>[] graph;
    public int solution(int N, int[][] edge) {
        n = N;
        dist = new int[n+1];
        visited = new boolean[n+1];
        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {1,1});
        visited[1]= true;
        
        int max = 0;
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int cd = cur[1];

            for(int next : graph[cur[0]]){
                if(visited[next]) continue;
                visited[next] = true;
                
                int nd = cd + 1;
                dq.offer(new int[] {next, nd});
                max = Math.max(max,nd);
                dist[next] = nd;
            }
        }
        
        
        int answer = 0;
        for(int d : dist){
            if (d == max) answer++;
        }
        return answer;
    }
}
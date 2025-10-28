import java.util.*;

class Solution {
    int n;
    List<Integer>[] graph;
    int[] dist;
    boolean[] visited;
    int max = 0;
    
    void bfs(int sidx){
        Deque<int[]>dq = new ArrayDeque<>();
        dq.offer(new int[] {1,0});
        visited[1] = true;
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            
            for(int next : graph[cur[0]]){
                if(visited[next]) continue;
                visited[next] = true;
                dist[next] = cur[1] + 1;
                dq.offer(new int[] {next, dist[next]});
                
                max = Math.max(dist[next], max);
            }
        }
    }
    public int solution(int N, int[][] edge) {
        n = N;
        graph = new List[N+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        dist = new int[n+1];
        visited = new boolean[n+1];
        
        for(int[] e : edge){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        bfs(1);
        
        int cnt = 0;
        for(int d : dist){
            if(d == max) cnt++;
        }
        
        return cnt;
    }
}
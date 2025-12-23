import java.util.*;

class Solution {
    boolean[][] visited;
    int n,m;
    int[] result;
    int[] dx = new int[] {0,0,1,-1};
    int[] dy = new int[] {1,-1,0,0};
    int[][] data;
    
    void bfs(int sx, int sy){
        Deque<int[]>dq = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        dq.offer(new int[] {sx,sy});
        visited[sx][sy] = true;
        set.add(sy);
        int cnt = 1;
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            for(int f = 0; f<4;f++){
                int nx = x + dx[f];
                int ny = y + dy[f];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(data[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                
                dq.offer(new int[] {nx,ny});
                cnt++;
                set.add(ny);
            }
        }
        
        for(int val : set){
            result[val] += cnt;
        }
              
    }
    public int solution(int[][] land) {
        data = land;
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        result = new int[m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    bfs(i,j);
                }
            }
        }
        
        
        
        
        int answer = 0;
        for(int val : result){
            answer = Math.max(answer,val);
        }
        return answer;
    }
}
import java.util.*;
class Solution {
    int n,m;    
    int[][] data;
    boolean[][] visited;
    int[] dx = new int[] {0,0,1,-1};
    int[] dy = new int[] {1,-1,0,0};
    int[] result;
    
    
    void bfs(int sx,int sy){
        Set<Integer> set = new HashSet<>();
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {sx,sy});
        int cnt = 1;
        set.add(sy);
        visited[sx][sy] = true;
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            
            for(int f=0;f<4;f++){
                int nx = cur[0] + dx[f];
                int ny = cur[1] + dy[f];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(visited[nx][ny] || data[nx][ny] != 1) continue;
                visited[nx][ny] = true;
                dq.offer(new int[] {nx,ny});
                set.add(ny);
                cnt++;
                
            }
        }
        
        for(int y : set){
            result[y] += cnt;
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
                if(data[i][j] == 1 && !visited[i][j] ){
                    bfs(i,j);
                }
            }
        }
        
        
        int max = 0;
        for(int val : result){
            max = Math.max(max,val);
        }
        return max;
    }
}
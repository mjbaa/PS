import java.io.*;
import java.util.*;

class Solution {
    static int[][] data;
    static boolean[][] visited;
    static int n,m;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[] result;
    
    void bfs(int sx,int sy){
        Set<Integer> set = new HashSet<>();
        Deque<int[]> dq = new ArrayDeque<>();
        
        int cnt = 1;
        dq.offer(new int[] {sx ,sy});
        visited[sx][sy] = true;
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            
            set.add(cur[1]);
            
            for(int f=0;f<4;f++){
                int nx = cur[0] + dx[f];
                int ny = cur[1] + dy[f];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(data[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                dq.offer(new int[] {nx,ny});
                cnt++;
            }
        }
        
        for(int col : set){
            result[col] += cnt;
        }
        
    }
    public int solution(int[][] land) {
        data = land;
        n = data.length;
        m = data[0].length;
        visited = new boolean[n][m];
        result = new int[m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(data[i][j] == 0) continue;
                if(visited[i][j]) continue;
                
                bfs(i,j);
            }
        }
        
        int max = 0;
        for(int i=0;i<m;i++){
            max = Math.max(max, result[i]);
        }
        return max;
    }
}
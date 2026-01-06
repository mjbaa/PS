import java.util.*;
import java.io.*;

class Solution {
    int n,m;
    int[][] data;
    boolean[][] visited;
    int[] result;
    
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    
    void bfs(int sx,int sy){
        Deque<int[]> dq = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        
        dq.offer(new int[] {sx,sy});
        set.add(sy);
        visited[sx][sy] = true;
        int cnt = 1;
        
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
                if(data[i][j] == 1 && !visited[i][j]){
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
import java.io.*;
import java.util.*;

class Solution {
    int[][] data;
    int n,m;
    boolean[][] visited;
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    int[] result;
    
    void dfs(int sx,int sy){
        Deque<int[]> dq = new ArrayDeque<>();
        Set<Integer> ySet = new HashSet<>();
        ySet.add(sy);
        dq.offer(new int[] {sx,sy});
        visited[sx][sy] = true;
        
        int cnt = 1;
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            
            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(data[nx][ny] != 1 ) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                dq.offer(new int[] {nx,ny});
                ySet.add(ny);
                cnt++;
            }
            
        }
        
        for(int y : ySet){
            result[y] += cnt;
        }
    }
    public int solution(int[][] land) {
        data = land;
        n = data.length;
        m = data[0].length;
        result = new int[m];
        
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(data[i][j] == 1 && !visited[i][j]){
                    dfs(i,j);
                }
            }
        }
        
        int max = 0;
        for(int i=0;i<m;i++){
            max = Math.max(max, result[i]);
        }
        int answer = max;
        return answer;
    }
}
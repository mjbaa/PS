import java.io.*;
import java.util.*;

class Solution {
    static int n,m;
    static int[][] data;
    static int[] result;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    void bfs(int sx, int sy){
        Deque<int[]> dq = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        dq.offer(new int[] {sx,sy});
        int cnt = 1;
        visited[sx][sy] = true;
        set.add(sy);
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            
            for(int f=0;f<4;f++){
                int nx = cur[0] + dx[f];
                int ny = cur[1] + dy[f];
                
                if(nx < 0 || ny < 0 || nx >=n || ny >= m ) continue;
                if(visited[nx][ny]) continue;
                if(data[nx][ny] == 0) continue;
                
                dq.offer(new int[] {nx,ny});
                cnt++;
                visited[nx][ny] = true;
                set.add(ny);
            }
        }
        
        for(int y : set){
            result[y] += cnt;
        }
        
    }
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        data = land;
        result = new int[m];
        visited = new boolean[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(data[i][j] == 1 && !visited[i][j]){
                    bfs(i,j);
                }
            }
        }
        
        int max = 0;
        for(int val : result){
            max = Math.max(val,max);
        }
        int answer = max;
        return answer;
    }
}
import java.util.*;

class Solution {
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        int[][] visited = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        
        queue.offer(new int[] {0,0});
        visited[0][0] = 1;
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == n-1 && current[1] == m-1) return visited[current[0]][current[1]];
            
            for(int i=0;i<4;i++){
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(0<=nx&&nx<n&&0<=ny&&ny<m&&maps[nx][ny] == 1 && visited[nx][ny]==0){
                    visited[nx][ny] = visited[current[0]][current[1]] +1;
                    queue.offer(new int[] {nx,ny});
                    
                }
            }
        }
        return -1;
    }
}
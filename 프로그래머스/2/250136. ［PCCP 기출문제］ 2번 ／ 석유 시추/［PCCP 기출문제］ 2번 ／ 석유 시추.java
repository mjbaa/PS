import java.io.*;
import java.util.*;

class Solution {
    int n,m;
    boolean[][] visited;
    int[] dx = new int[] {1,-1,0,0};
    int[] dy = new int[] {0,0,1,-1};
    int[] result;
    int[][] data;
    
    void bfs(int x,int y){
        Set<Integer> set = new HashSet<>();
        Deque<int[]> dq = new ArrayDeque<>();
        int sum = 0;
        visited[x][y] = true;
        dq.offer(new int[] {x,y});
        set.add(y);
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            if(data[cur[0]][cur[1]] == 1) sum++;
            
            for(int f=0;f<4;f++){
                int nx = cur[0] + dx[f];
                int ny = cur[1] + dy[f];
                
                if(nx<0 || ny < 0 || nx >= n || ny >= m) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if(data[nx][ny] == 0) continue;
                dq.offer(new int[] {nx,ny});
                set.add(ny);
            }
        }
        for(int s : set){
            result[s] += sum;
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
                if(visited[i][j]) continue;
                if(data[i][j] == 0) continue;
                bfs(i,j);
            }
        }
        
        int max = 0;
        for(int c : result){
            max = Math.max(max, c);
        }
        
        return max;
    }
}
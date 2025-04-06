import java.util.*;

class Solution {
    static class Point {
        int x, y, dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        
        int startX = 0, startY = 0;
        int leverX = 0, leverY = 0;
        int endX = 0, endY = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    leverX = i;
                    leverY = j;
                } else if (c == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }
        
        int distToLever = bfs(maps, startX, startY, 'L');
        if (distToLever == -1) {
            return -1;
        }
        
        int distToExit = bfs(maps, leverX, leverY, 'E');
        if (distToExit == -1) {
            return -1;
        }
        
        return distToLever + distToExit;
    }
    
    private int bfs(String[] maps, int startX, int startY, char target) {
        int n = maps.length;
        int m = maps[0].length();
        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();
        
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            
            if (maps[p.x].charAt(p.y) == target) {
                return p.dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, p.dist + 1));
                    }
                }
            }
        }
        
        return -1;
    }
}

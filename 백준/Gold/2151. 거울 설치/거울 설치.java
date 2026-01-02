import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] data; // 1 : 거울 설치 가능, 0 : 빈 칸, -1 : 벽
    static int[][][] dist;

    static int sx,sy,tx,ty;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static boolean notRange(int x, int y){
        return (x<0||y<0||x>=n||y>=n);
    }

    static void bfs(){
        Deque<int[]> dq = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            dist[sx][sy][d] = 0;
            dq.offer(new int[]{sx, sy, d});
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];

            int nx = x + dx[cur[2]];
            int ny = y + dy[cur[2]];

            if(!notRange(nx, ny) && data[nx][ny] != -1){
                if(dist[nx][ny][dir] > dist[x][y][dir]){
                    dist[nx][ny][dir] = dist[x][y][dir];
                    dq.offer(new int[]{nx, ny,dir});
                }
            }

            if(data[x][y] == 1){
                for(int f=0;f<4;f++){
                    if(dir == f || (dir+2)%4 == f) continue;
                    nx = x + dx[f];
                    ny = y + dy[f];
                    if(notRange(nx, ny) || data[nx][ny] == -1) continue;
                    if(dist[nx][ny][f] > dist[x][y][dir]+1){
                        dist[nx][ny][f] = dist[x][y][dir]+1;
                        dq.offer(new int[]{nx, ny,f});
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];

        boolean isFirst = true;
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < n; j++) {
                char val = line.charAt(j);
                if(val == '*') data[i][j] = -1;
                else if(val == '!') data[i][j] = 1;
                else if(val == '#'){
                    if(isFirst) {
                        sx = i;
                        sy = j;
                        isFirst = false;
                    }else{
                        tx = i;
                        ty = j;
                    }
                }
            }
        }

        dist = new int[n][n][4];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);

        bfs();

        int result = Integer.MAX_VALUE;
        for(int f=0;f<4;f++){
            result = Math.min(result, dist[tx][ty][f]);
        }
        System.out.println(result);
    }
}

import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][][] dist;
    static int[][] data;


    static void bfs(){
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0,0,0});
        dist[0][0][0] = 1;
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            int z = cur[2];
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >=m) continue;
                if(data[nx][ny] == 0){
                    if(dist[nx][ny][z] != 0) continue;
                    dist[nx][ny][z] = dist[x][y][z] + 1;
                    dq.offer(new int[]{nx,ny,z});
                }else{//벽
                    if(z < k){
                        if(dist[nx][ny][z+1] != 0) continue;
                        dist[nx][ny][z+1] = dist[x][y][z] + 1;
                        dq.offer(new int[]{nx,ny,z+1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[n][m][k+1];
        data = new int[n][m];
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                data[i][j] = Integer.parseInt(line.charAt(j)+"");
            }
        }

        bfs();

        int min = Integer.MAX_VALUE;
        for(int i=0;i<=k;i++){
            int value = dist[n-1][m-1][i];
            if(value == 0) continue;
            min = Math.min(min,value);
        }

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
}
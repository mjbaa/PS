import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static int[][] data;
    static int[][][] dist;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static void bfs(){
        dist[0][0][0] = 1;
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {0,0,0,1}); // 좌표, 차원, 시간

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0],y = cur[1], z = cur[2], time = cur[3];

            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx<0 || ny <0 || nx >= n || ny >= m) continue;
                if(data[nx][ny]==0){
                    if(dist[nx][ny][z]!=0) continue;

                    dist[nx][ny][z] = time+1;
                    dq.offer(new int[] {nx,ny,z, time+1});
                }else{// 벽
                    if(z < k){
                        if(dist[nx][ny][z+1]!=0) continue;

                        if(time % 2 == 1){ // 홀수 : 낮
                            dist[nx][ny][z+1] = time + 1;
                            dq.offer(new int[] {nx,ny,z+1, time+1});
                        }else{ // 밤 : 이동 안하고 하루 대기
                            if (dist[x][y][z] < time + 1) {// 하루만 대기할 수 있도록 방문처리 해줘야함
                                dist[x][y][z] = time + 1;
                                dq.offer(new int[] {x, y, z, time + 1});
                            }
                        }
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

        data = new int[n][m];
        dist = new int[n][m][k+1];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j=0;j<m;j++){
                data[i][j] = line.charAt(j)-'0';
            }
        }

        bfs();

        int min = Integer.MAX_VALUE;
        for(int i=0;i<=k;i++){
            int val = dist[n-1][m-1][i];
            if(val == 0) continue;
            min = Math.min(min,val);
        }

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
}
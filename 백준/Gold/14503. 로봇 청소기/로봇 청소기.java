import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1,0,1,0}; // 북서남동 ( 반시계 방향 )
    static int[] dy = {0,-1,0,1};
    static int[][] map;
    static boolean[][] cleaned;
    static int cnt = 0;
    static int didx = 0;
    static int n,m;

    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cleaned = new boolean[n][m];
        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        if(d == 1){
            didx = 3;
        }else if(d == 2){
            didx = 2;
        }else if(d == 3){
            didx = 1;
        }else{
            didx = 0;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            if(map[x][y] == 0 && !cleaned[x][y]){
                cleaned[x][y] = true;
                cnt++;
            }

            boolean canClean = false;
            for(int i=0;i<4;i++){ // 주변 청소 가능 구역 탐색
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1) continue;
                if(!cleaned[nx][ny]){
                    canClean = true;
                    break;
                }
            }

            if(!canClean){
                int ndidx = (didx+2) % 4; // 후진
                int nx = x + dx[ndidx];
                int ny = y + dy[ndidx];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1) {
                    break;
                }else{
                    x = nx;
                    y = ny;
                }
            }else{
                for(int i=1;i<=4;i++){
                    int ndidx = (didx + i) % 4;
                    int nx = x + dx[ndidx];
                    int ny = y + dy[ndidx];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1) continue;
                    if(!cleaned[nx][ny]){
                        x = nx;
                        y = ny;
                        didx = ndidx;
                        break;
                    }

                }
            }


        }
        System.out.println(cnt);

    }

}

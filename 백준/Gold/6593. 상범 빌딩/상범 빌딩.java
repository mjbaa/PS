import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0,0,0,0,1,-1};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {1,-1,0,0,0,0};
    static int[][][] data;
    static boolean[][][] visited;
    static int l,r,c;
    static int sx,sy,sz;

    static int bfs(){
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sz,sx,sy,0});
        visited[sz][sx][sy] = true;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int z = cur[0];
            int x = cur[1];
            int y = cur[2];
            int time = cur[3];

            if(data[z][x][y] == 1) return time;

            for(int f = 0; f<6;f++){
                int nz = z+dz[f];
                int nx = x+dx[f];
                int ny = y+dy[f];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= r || ny >= c || nz >= l) continue;
                if(data[nz][nx][ny] == -1) continue;
                if(visited[nz][nx][ny]) continue;
                visited[nz][nx][ny] = true;
                dq.offer(new int[]{nz,nx,ny,time+1});

            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            if(l == 0) break;
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            data = new int[l][r][c];
            visited = new boolean[l][r][c];

            for(int i=0;i<l;i++){
                for(int j=0;j<r;j++){
                    String line = br.readLine();
                    for(int k=0;k<c;k++){
                        char val = line.charAt(k);
                        if(val == 'S'){
                            sz = i;
                            sx = j;
                            sy = k;
                        }else if(val == '#'){
                            data[i][j][k] = -1;
                        }else if(val == 'E'){
                            data[i][j][k] = 1;
                        }
                    }
                }
                String temp = br.readLine();
            }

            int result = bfs();
            if(result == -1){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in "+result+" minute(s).");
            }
        }

    }
}

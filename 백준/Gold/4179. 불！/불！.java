import java.io.*;
import java.util.*;

public class Main {
    static int r,c;
    static int sx,sy;
    static int[][] data;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static Deque<int[]> fires = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        data = new int[r][c];

        for(int i=0;i<r;i++){
            String line = br.readLine();
            for(int j=0;j<c;j++){
                char val = line.charAt(j);
                if(val == '#'){//벽
                    data[i][j] = -2;
                }else if(val == '.'){//빈칸
                    data[i][j] = -1;
                }else if(val == 'F'){//불 ( 번진 시간 )
                    fires.add(new int[]{i,j,0});
                    data[i][j] = 0;
                }else if(val == 'J'){
                    sx = i;
                    sy = j;
                    data[i][j] = -1;
                }
            }
        }

        while(!fires.isEmpty()){
            int[] fire = fires.poll();

            int x = fire[0];
            int y = fire[1];
            int time = fire[2];

            for(int f=0;f<4;f++){
                int nx = x + dx[f];
                int ny = y + dy[f];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if(data[nx][ny] == -1){ // 빈칸
                    data[nx][ny] = time+1;
                    fires.add(new int[]{nx,ny,time+1});
                }

            }

        }
        boolean[][] visited = new boolean[r][c];
        visited[sx][sy] = true;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx,sy,0});

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            if(x <= 0 || y <= 0 || x >= r-1 || y >= c-1) { // 가장자리 도착
                System.out.println(time+1);
                return;
            }

            for(int f = 0; f<4;f++){
                int nx = cur[0] + dx[f];
                int ny = cur[1] + dy[f];

                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                
                if(data[nx][ny] == -2) continue;
                if(data[nx][ny] != -1 && time+1 >= data[nx][ny]) continue;

                dq.offer(new int[]{nx,ny,time+1});


            }
        }
        System.out.println("IMPOSSIBLE");

    }


}

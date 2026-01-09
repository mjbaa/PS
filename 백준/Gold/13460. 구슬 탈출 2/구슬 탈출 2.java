import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static char[][] data;
    static boolean[][][][] visited;
    static int tx,ty;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};


    static int result = -1;

    static class State {
        int bx, by, rx, ry, cnt;
        State(int bx, int by, int rx, int ry, int cnt) {
            this.bx = bx;
            this.by = by;
            this.rx = rx;
            this.ry = ry;
            this.cnt = cnt;
        }
    }

    // 구슬 하나 이동
    static int[] move(int x, int y, int dir) {
        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (data[nx][ny] == '#') break;
            x = nx;
            y = ny;
            if (x == tx && y == ty) break;
        }
        return new int[]{x, y};
    }


    static void bfs(int b1,int b2, int r1, int r2){
        Deque<State> dq = new ArrayDeque<>();
        dq.offer(new State(b1, b2, r1, r2, 0));
        visited[b1][b2][r1][r2] = true;

        while(!dq.isEmpty()){
            State cur = dq.poll();

            if(cur.cnt >= 10) break;

            for(int f=0;f<4;f++){
                int[] nb = move(cur.bx, cur.by, f);
                int[] nr = move(cur.rx, cur.ry, f);

                //구멍 빠진 경우 -> 겹침 없음
                if(nb[0] == tx && nb[1] == ty) continue;

                if(nr[0] == tx && nr[1] == ty) {
                    result = cur.cnt+1;
                    return;
                }

                if (nb[0] == nr[0] && nb[1] == nr[1]) {
                    int bdist = Math.abs(nb[0] - cur.bx) + Math.abs(nb[1] - cur.by);
                    int rdist = Math.abs(nr[0] - cur.rx) + Math.abs(nr[1] - cur.ry);

                    //늦게 온 공 한칸 뒤로 후진
                    if(bdist < rdist){ //b가 먼저 도착
                        nr[0] -= dx[f];
                        nr[1] -= dy[f];
                    }else{
                        nb[0] -= dx[f];
                        nb[1] -= dy[f];
                    }
                }

                if(visited[nb[0]][nb[1]][nr[0]][nr[1]]) continue;
                visited[nb[0]][nb[1]][nr[0]][nr[1]] = true;
                dq.offer(new State(nb[0],nb[1],nr[0],nr[1],cur.cnt+1));
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new char[n][m];
        int b1=0, b2=0, r1=0, r2=0;
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                char val = line.charAt(j);
                data[i][j] = val;
                if(val == 'O'){
                    tx = i;
                    ty = j;
                }else if(val == 'B'){
                    b1 = i;
                    b2 = j;
                }else if(val == 'R'){
                    r1 = i;
                    r2 = j;
                }
            }
        }

        visited = new boolean[n][m][n][m];

        bfs(b1,b2,r1,r2);

        System.out.println(result);

    }
}

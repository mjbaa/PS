import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] data;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static boolean[][] visited;

    static void bfs(int sx,int sy, boolean disabled){
        Set<Character> set = new HashSet<>();
        set.add(data[sx][sy]);
        if(data[sx][sy] == 'R' || data[sx][sy] == 'G'){
            if(disabled) {
                set.add('R');
                set.add('G');
            }
        }


        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx,sy});
        visited[sx][sy] = true;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0],y = cur[1];

            for(int f = 0; f<4;f++){
                int nx = x + dx[f];
                int ny = y + dy[f];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny]) continue;

                if(set.contains(data[nx][ny])) {
                    visited[nx][ny] = true;
                    dq.offer(new int[]{nx,ny});
                }

            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new char[n][n];

        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<n;j++){
                data[i][j] = line.charAt(j);
            }
        }

        //StringBuilder sb = new StringBuilder();
        visited = new boolean[n][n];
        int cnt1 = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]) {
                    cnt1++;
                    bfs(i,j,false);
                }
            }
        }

        visited = new boolean[n][n];
        int cnt2 = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]) {
                    cnt2++;
                    bfs(i,j,true);
                }
            }
        }

        System.out.println(cnt1+" "+cnt2);

        //System.out.println(sb);
    }
}

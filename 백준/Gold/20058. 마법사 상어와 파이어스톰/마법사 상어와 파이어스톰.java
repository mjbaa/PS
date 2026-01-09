import java.io.*;
import java.util.*;

public class Main {
    static int n,q;
    static int[][] data;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited;
    static int sum = 0;
    static int max = 0;

    static void rotate(int sx,int sy, int len){
        int[][] temp = new int[len][len];

        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                temp[j][len-1-i] = data[sx+i][sy+j];
            }
        }

        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                data[sx+i][sy+j]=temp[i][j];
            }
        }

    }


    static void fire(int val){
        int len = 1 << val;
        for(int i=0;i<n;i+=len){
            for(int j=0;j<n;j+=len){
                rotate(i,j, len);
            }
        }

        int[][] temp = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                temp[i][j] = data[i][j];
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(data[i][j] == 0) continue;
                int cnt = 0;
                for(int f=0;f<4;f++){
                    int nx = i + dx[f];
                    int ny = j + dy[f];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if(data[nx][ny] != 0) cnt++;
                }

                if(cnt < 3) temp[i][j] = data[i][j]-1;

            }
        }

        data = temp;
    }

    static void bfs(int sx,int sy){
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx,sy});
        sum+=data[sx][sy];
        int cnt = 1;
        visited[sx][sy] = true;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();

            for(int f=0;f<4;f++){
                int nx = cur[0] + dx[f];
                int ny = cur[1] + dy[f];

                if(nx < 0 || ny < 0 ||nx >= n || ny >= n) continue;
                if(data[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;

                dq.offer(new int[]{nx,ny});
                cnt++;
                sum+=data[nx][ny];
            }
        }

        max = Math.max(cnt,max);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = 1 << Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        data = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<q;i++){
            int L = Integer.parseInt(st.nextToken());
            fire(L);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(data[i][j] != 0 && !visited[i][j]) bfs(i,j);
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }
}

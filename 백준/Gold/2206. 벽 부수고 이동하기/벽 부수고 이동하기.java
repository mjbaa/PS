import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[][] data;
    static int[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];
        visited = new int[n][m][2];

        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                data[i][j] = line.charAt(j)-'0';
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0,0,0});
        visited[0][0][0] = 1;
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0],y = cur[1],z = cur[2];
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx >=n || ny>=m) continue;
                if(visited[nx][ny][z] != 0) continue;

                if(data[nx][ny] == 0){
                    visited[nx][ny][z] = visited[x][y][z] + 1;
                    dq.offer(new int[]{nx,ny,z});
                }else{
                    if(z==0){
                        visited[nx][ny][z+1] = visited[x][y][z]+1;
                        dq.offer(new int[]{nx,ny,z+1});

                    }
                }


            }
        }

        int r1 = visited[n-1][m-1][0];
        int r2 = visited[n-1][m-1][1];

        if(r1==0 && r2==0) System.out.println(-1);
        else if(r1==0 && r2!=0) System.out.println(r2);
        else if(r2==0 && r1!=0) System.out.println(r1);
        else System.out.println(Math.min(r1,r2));

    }
}
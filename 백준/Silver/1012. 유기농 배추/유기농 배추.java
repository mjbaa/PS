import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean[][] data = new boolean[n][m];
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];

            for(int i=0;i<k;i++){

                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                data[r][c] = true;

            }
            int[] dx = {0,0,1,-1};
            int[] dy = {1,-1,0,0};

            int cnt = 0;

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(data[i][j] && !visited[i][j] ){
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                        cnt++;
                        while(!q.isEmpty()){
                            int[] cur = q.poll();

                            for(int f=0;f<4;f++){
                                int nx = cur[0] + dx[f];
                                int ny = cur[1] + dy[f];

                                if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                                    continue;
                                }
                                if(data[nx][ny] && !visited[nx][ny]){
                                    q.add(new int[]{nx,ny});
                                    visited[nx][ny] = true;
                                }
                            }


                        }
                    }
                }
            }

            System.out.println(cnt);




        }
    }
}

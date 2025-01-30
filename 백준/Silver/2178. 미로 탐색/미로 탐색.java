import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n= Integer.parseInt(st.nextToken());
        int m= Integer.parseInt(st.nextToken());

        int[][] visited = new int[n][m];

        Queue<int[]> q = new LinkedList<>();


        int[][] data = new int[n][m];
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                data[i][j] = line.charAt(j)-'0';
            }
        }
        int[] dx={0,0,1,-1};
        int[] dy = {1,-1,0,0};

        q.offer(new int[] {0,0});
        visited[0][0] = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();



            for(int i=0;i<4;i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx >= 0 && ny >= 0&& nx<n && ny < m){
                    if(visited[nx][ny]==0 && data[nx][ny]==1){
                        q.offer(new int[] {nx,ny});
                        visited[nx][ny] = visited[cur[0]][cur[1]]+1;
                    }
                }
            }
        }

        System.out.println(visited[n-1][m-1]);

    }
}

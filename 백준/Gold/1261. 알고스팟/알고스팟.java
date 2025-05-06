import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] dist;
    static int[][] data;

    static int n,m;

    static void dijk(int sx,int sy){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        dist[sx][sy] = 0;
        pq.add(new int[]{sx,sy,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0],y = cur[1],w=cur[2];

            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                int nw = data[nx][ny];

                if(dist[nx][ny] > dist[x][y] + nw){
                    dist[nx][ny] = dist[x][y] + nw;
                    pq.add(new int[]{nx,ny,dist[nx][ny]});
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        data = new int[n][m];
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                data[i][j] = line.charAt(j)-'0';
            }
        }

        dijk(0,0);

        System.out.println(dist[n-1][m-1]);
    }
}

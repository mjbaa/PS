import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x,y, weight,preidx;
        Node(int x,int y, int weight, int preidx){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.preidx = preidx;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[][] data;
    static int[][][] dist;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static int n,m;

    static int sx,sy,tx,ty;

    static void dijks(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int d = 0; d < 4; d++){
            dist[sx][sy][d] = 0;
            pq.offer(new Node(sx, sy, 0, d));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.x][cur.y][cur.preidx] < cur.weight) continue;

            for(int f = 0; f<4;f++){
                int nx = cur.x+dx[f];
                int ny = cur.y+dy[f];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(data[nx][ny] == -1) continue;

                int nd = cur.weight;
                if (cur.preidx != f) nd++;

                if(dist[nx][ny][f] > nd){
                    dist[nx][ny][f] = nd;
                    pq.offer(new Node(nx, ny, nd, f));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        dist = new int[n][m][4];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        boolean first = true;

        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                char val = line.charAt(j);
                if(val == 'C'){
                    if(first){
                        sx = i; sy = j;
                        first = false;
                    }else{
                        tx = i; ty = j;
                    }
                }else if(val == '*'){
                    data[i][j] = -1;
                }
            }
        }

        dijks();

        int ans = Integer.MAX_VALUE;
        for(int d = 0; d < 4; d++){
            ans = Math.min(ans, dist[tx][ty][d]);
        }
        System.out.println(ans);
    }
}

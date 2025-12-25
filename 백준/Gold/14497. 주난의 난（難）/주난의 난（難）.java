import java.io.*;
import java.util.*;


public class Main {
    static class Node implements Comparable<Node> {
        int x,y, weight;
        Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n,m;
    static int sx,sy,tx,ty;
    static boolean[][] data;
    static int[][] dist;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static void dijks(){
        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(new Node(sx,sy,0));
        dist[sx][sy] = 0;

        while(!dq.isEmpty()){
            Node cur = dq.poll();

            for(int f=0;f<4;f++){
                int nx = cur.x+dx[f];
                int ny = cur.y+dy[f];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                int d = 0;
                if(data[nx][ny]) d++;
                if(dist[nx][ny] > dist[cur.x][cur.y] + d){
                    dist[nx][ny] = dist[cur.x][cur.y] + d;
                    dq.add(new Node(nx,ny,dist[nx][ny]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        tx = Integer.parseInt(st.nextToken())-1;
        ty = Integer.parseInt(st.nextToken())-1;

        data = new boolean[n][m];
        dist = new int[n][m];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                char val = line.charAt(j);
                if(val == '0'){
                    data[i][j] = false;
                }else{
                    data[i][j] = true;
                }
            }
        }

        dijks();

        System.out.println(dist[tx][ty]);

    }


}

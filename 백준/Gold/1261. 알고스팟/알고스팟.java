import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int x,y, weight;
        Node(int x,int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int n,m;
    static boolean[][] map;
    static int[][] dist;
    static int[] dx = new int[] {0,0,1,-1};
    static int[] dy = new int[] {1,-1,0,0};

    static void dijks(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0,0,0));
        dist[0][0] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.weight > dist[cur.x][cur.y]) continue;

            for(int f = 0; f<4;f++){
                int nx = cur.x + dx[f];
                int ny = cur.y + dy[f];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                int nd = dist[cur.x][cur.y];
                if(map[nx][ny]) nd++;

                if(nd < dist[nx][ny]) {
                    dist[nx][ny] = nd;
                    pq.offer(new Node(nx,ny,nd));
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n =  Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        dist = new int[n][m];

        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                char val = line.charAt(j);
                if(val =='0'){
                    map[i][j] = false;
                }else{
                    map[i][j] = true;
                }
            }
        }
        for(int[] row : dist){
            Arrays.fill(row,Integer.MAX_VALUE);
        }

        dijks();

        System.out.println(dist[n-1][m-1]);
    }
}

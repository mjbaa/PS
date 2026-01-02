import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x,y,weight, preidx;
        Node(int x, int y, int weight, int preidx){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.preidx = preidx;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n;
    static int[][] data; // 1 : 거울 설치 가능, 0 : 빈 칸, -1 : 벽
    static int[][][] dist;

    static int sx,sy,tx,ty;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static boolean notRange(int x, int y){
        return (x<0||y<0||x>=n||y>=n);
    }

    static void dijks(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int f=0;f<4;f++){
            pq.offer(new Node(sx,sy,0,f));
            dist[sx][sy][f] = 0;
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int weight = cur.weight;
            int preidx = cur.preidx;
            
            if(dist[x][y][preidx] < weight) continue;
            
            boolean canMirror = false;
            if(data[x][y] == 1) canMirror = true;

            if(canMirror){
                for(int f=0;f<4;f++){
                    if((preidx+2)%4 == f) continue; // 역방향 불가능
                    if(preidx == f) continue; // 순방향 제외

                    int nx = x+dx[f];
                    int ny = y+dy[f];
                    if(notRange(nx,ny)) continue;
                    if(data[nx][ny] == -1) continue;
                    if(dist[nx][ny][f] > weight+1){
                        dist[nx][ny][f] = weight+1;
                        pq.offer(new Node(nx,ny,dist[nx][ny][f],f));
                    }
                }
            }

            //순방향 : 언제든 가능
            int nx = x+dx[preidx];
            int ny = y+dy[preidx];
            if(notRange(nx,ny)) continue;
            if(data[nx][ny] == -1) continue;
            if(dist[nx][ny][preidx] > weight){
                dist[nx][ny][preidx] = weight;
                pq.offer(new Node(nx,ny,dist[nx][ny][preidx],preidx));
            }


        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];

        boolean isFirst = true;
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < n; j++) {
                char val = line.charAt(j);
                if(val == '*') data[i][j] = -1;
                else if(val == '!') data[i][j] = 1;
                else if(val == '#'){
                    if(isFirst) {
                        sx = i;
                        sy = j;
                        isFirst = false;
                    }else{
                        tx = i;
                        ty = j;
                    }
                }
            }
        }

        dist = new int[n][n][4];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        dijks();

        int min = Integer.MAX_VALUE;
        for(int f=0;f<4;f++){
            min = Math.min(min,dist[tx][ty][f]);
        }
        System.out.println(min);
    }
}

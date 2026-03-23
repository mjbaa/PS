import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int x,y,weight;
        int preDir;

        Node(int x,int y, int weight, int preDir){
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.preDir = preDir;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int w,h;
    static int[][][] dist;
    static char[][] data;
    static int[] location = new int[4];
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static boolean cantGo(int x,int y){
        return x<0 || y<0 || x >= h || y >= w || data[x][y] == '*';
    }

    static void dijks(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i=0;i<4;i++){
            dist[location[0]][location[1]][i] = 0;
            pq.offer(new Node(location[0], location[1], 0, i));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.weight > dist[cur.x][cur.y][cur.preDir]) continue;

            for(int f=0;f<4;f++){
                int nx = cur.x + dx[f];
                int ny = cur.y + dy[f];
                if(cantGo(nx,ny)) continue;

                int newWeight = cur.weight;
                if(cur.preDir%2 != f%2) newWeight++;

                if(dist[nx][ny][f] > newWeight){
                    dist[nx][ny][f] = newWeight;
                    pq.offer(new Node(nx,ny,dist[nx][ny][f],f));
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        dist = new int[h][w][4];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        data = new char[h][w];

        boolean first = true;
        for(int i=0;i<h;i++){
            String line = br.readLine();
            for(int j=0;j<w;j++){
                char c = line.charAt(j);
                data[i][j] = c;

                if(c == 'C'){
                    if(first){
                        location[0] = i;
                        location[1] = j;
                        first = false;
                    }else{
                        location[2] = i;
                        location[3] = j;
                    }
                }
            }
        }

        dijks();

        int ans = Integer.MAX_VALUE;
        for(int f=0;f<4;f++){
            ans = Math.min(ans, dist[location[2]][location[3]][f]);
        }

        System.out.println(ans);



    }

}

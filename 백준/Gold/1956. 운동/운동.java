import java.io.*;
import java.util.*;

public class Main {
    static int v,e;
    static int[][] dist;
    static int INF = 9999999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist = new int[v+1][v+1];
        for(int i=1;i<=v;i++){
            Arrays.fill(dist[i],INF);
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            dist[s][d] = Math.min(dist[s][d],w);
        }

        for(int k=1;k<=v;k++){
            for(int i=1;i<=v;i++){
                for(int j=1;j<=v;j++){
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=1;i<=v;i++){
            for(int j=1;j<=v;j++){
                min = Math.min(min,dist[i][j] + dist[j][i]);
            }
        }
        if(min >= INF) System.out.println(-1);
        else System.out.println(min);

    }
}

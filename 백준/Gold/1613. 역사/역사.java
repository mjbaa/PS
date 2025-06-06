import java.util.*;
import java.io.*;

public class Main {
    static int n,k,s;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new int[n+1][n+1];
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            dist[first][second] = -1;
            dist[second][first] = 1;
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(dist[i][j] == 0 && dist[i][k] == 1 && dist[k][j] == 1){
                        dist[i][j] = 1;
                    }
                    if(dist[i][j] == 0 && dist[i][k] == -1 && dist[k][j] == -1){
                        dist[i][j] = -1;
                    }
                }
            }
        }

        s = Integer.parseInt(br.readLine());
        for(int i=0;i<s;i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            sb.append(dist[first][second]).append("\n");
        }

        System.out.println(sb);
    }
}
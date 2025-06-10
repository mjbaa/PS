import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] dist;
    static boolean[][] notNeed;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        notNeed = new boolean[n][n];

        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if (i == k || j == k || i == j) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return;
                    }

                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        notNeed[i][j] = true;
                    }
                }
            }
        }

        int sum = 0;

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(notNeed[i][j]) continue;
                sum += dist[i][j];

            }
        }
        System.out.println(sum);

    }
}
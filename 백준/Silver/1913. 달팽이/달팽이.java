import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int val = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        int[] dx = {1,0,-1,0};
        int[] dy={0,1,0,-1};

        int x = 0;
        int y = 0;
        int idx =0;
        map[x][y] = n*n;
        for(int i = n*n-1;i>0;){
            x = x+dx[idx];
            y = y+dy[idx];

            if(0<=x && x<n && 0<=y && y<n){
                if(map[x][y]==0){
                    map[x][y] = i;
                    i--;
                    continue;
                }

            }

            x-=dx[idx];
            y-=dy[idx];
            idx = (idx+1)%4;


        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j]+" ");
            }

            System.out.println();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==val) {
                    System.out.println((i+1) + " " + (j+1));
                }
            }
        }
    }
}

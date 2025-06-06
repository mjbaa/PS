import java.util.*;
import java.io.*;

public class Main {
  static int n,m;
  static int[][] dist;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());
    
    dist = new int[n+1][n+1];
    

    
    for(int i=0;i<m;i++){
      st = new StringTokenizer(br.readLine());
      int big = Integer.parseInt(st.nextToken());
      int small = Integer.parseInt(st.nextToken());
      dist[small][big] = 1;
      dist[big][small] = -1;
    }
    
    for(int k=1;k<=n;k++){
      for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
          if(dist[i][j] == 0 && dist[i][k] ==1 && dist[k][j] == 1 ){
            dist[i][j] = 1;
          }
          
          if(dist[i][j] == 0 && dist[i][k] ==-1 && dist[k][j] == -1 ){
            dist[i][j] = -1;
          }
        }
      }
    }
    
    for(int i=1;i<=n;i++){
      int cnt = 0;
      for(int j=1;j<=n;j++){
        if(i != j && dist[i][j] == 0) cnt++;
      }
      
      sb.append(cnt).append("\n");
    }
    
    System.out.println(sb);
    
  }
}
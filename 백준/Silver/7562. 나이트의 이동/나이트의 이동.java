import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        int[] dx = {1,1,2,2,-1,-1,-2,-2};
        int[] dy = {2,-2,1,-1,2,-2,1,-1};


        while(t-- > 0){
            Queue<int[]> q = new LinkedList<>();

            int l = Integer.parseInt(br.readLine());
            int[][] visited = new int[l][l];

            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            q.offer(new int[]{sx,sy});

            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());

            while(!q.isEmpty()){

                int[] cur = q.poll();
                if(cur[0] == fx && cur[1] == fy){
                    System.out.println(visited[fx][fy]);
                    break;
                }
                for(int i = 0; i < 8; i++){
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if(nx>=0 && ny>=0 && nx<l && ny<l){
                        if(visited[nx][ny]==0){
                            visited[nx][ny]=visited[cur[0]][cur[1]]+1;
                            q.offer(new int[]{nx,ny});
                        }
                    }
                }

            }

        }
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] data;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static List<int[]> airs = new ArrayList<>();
    static int cheeseSize;

    static int melting() {
        List<int[]> melted = new ArrayList<>();
        Deque<int[]> dq = new ArrayDeque<>();
        for(int[] air : airs){
            dq.offer(new int[]{air[0], air[1]});
            visited[air[0]][air[1]] = true;
        }
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            for(int f = 0; f < 4; f++){
                int nx = x + dx[f];
                int ny = y + dy[f];
                if(nx < 0 || nx >= n+2 || ny < 0 || ny >= m+2) continue;
                if(visited[nx][ny]) continue;
                
                if(data[nx][ny] == 0) {
                    dq.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }else{
                    melted.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        int result = melted.size();
        for(int[] ptr : melted){
            data[ptr[0]][ptr[1]] = 0;
        }
        airs = melted;
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n+2][m+2];
        visited = new boolean[n+2][m+2];
        
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=m;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j] == 1) cheeseSize++;
            }
        }
        airs.add(new int[]{0,0});
        int cnt = 0;
        int preSize = cheeseSize;
        while(cheeseSize > 0){
            cnt++;
            preSize = cheeseSize;
            visited = new boolean[n+2][m+2];
            cheeseSize -= melting();
        }
        System.out.println(cnt);
        System.out.println(preSize);
    }
}

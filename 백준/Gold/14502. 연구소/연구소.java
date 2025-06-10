import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[][] data;
    static boolean[][] visited;
    static List<int[]> virus = new ArrayList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static List<int[]> empty = new ArrayList<>();

    static int bfs(int sx, int sy){
        int cnt = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx,sy});
        visited[sx][sy] = true;
        cnt++;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0],y = cur[1];

            for(int k = 0; k < 4; k++){
                int nx = x+dx[k];
                int ny = y+dy[k];
                if(nx<0 || ny <0 || nx >= n || ny >= m) continue;
                if(visited[nx][ny]) continue;
                if(data[nx][ny] == 1) continue;
                dq.offer(new int[]{nx,ny});
                visited[nx][ny] = true;
                cnt++;
            }
        }

        return cnt;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        data = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int val = Integer.parseInt(st.nextToken());
                data[i][j] = val;

                if(val==2){
                    virus.add(new int[]{i,j});
                }

                if(val == 0){
                    empty.add(new int[]{i,j});
                }

            }
        }

        int max = Integer.MIN_VALUE;

        int emptySize = empty.size();
        for(int i=0;i<emptySize-2;i++){
            for(int j=i+1;j<emptySize-1;j++){
                for(int k=j+1;k<emptySize;k++){

                    int[] ptr1 = empty.get(i);
                    int[] ptr2 = empty.get(j);
                    int[] ptr3 = empty.get(k);

                    data[ptr1[0]][ptr1[1]] = 1;
                    data[ptr2[0]][ptr2[1]] = 1;
                    data[ptr3[0]][ptr3[1]] = 1;

                    int sum = 0;

                    visited = new boolean[n][m];
                    for(int[] v : virus){
                        bfs(v[0],v[1]);
                    }

                    int safeZone = 0;
                    for(int x=0;x<n;x++){
                        for(int y=0;y<m;y++){
                            if(data[x][y] == 0 && !visited[x][y]) safeZone++;
                        }
                    }
                    max = Math.max(max, safeZone);


                    data[ptr1[0]][ptr1[1]] = 0;
                    data[ptr2[0]][ptr2[1]] = 0;
                    data[ptr3[0]][ptr3[1]] = 0;

                }
            }
        }

        System.out.println(max);

    }
}
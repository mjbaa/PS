import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] data;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<int[]> cctvs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;


    static int[][][] dirs = {
            {},
            {{0},{1},{2},{3}},
            {{2,3},{0,1}},
            {{0,3},{3,1},{1,2},{2,0}},
            {{0,2,3},{0,1,3},{0,1,2},{1,2,3}},
            {{0,1,2,3}}
    };


    static int check(){
        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(data[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    static void dfs(int idx){
        if(idx == cctvs.size()){
            min = Math.min(min,check());
            return;
        }

        int[] cctv = cctvs.get(idx);
        int x = cctv[0];
        int y = cctv[1];
        int type = cctv[2];

        for(int[] dirSet : dirs[type]){
            List<int[]> changed = set(dirSet,x,y);
            dfs(idx+1);
            revoke(changed);
        }

    }

    static List<int[]> set(int[] dirSet, int sx, int sy){
        List<int[]> changed = new ArrayList<>();

        for(int d : dirSet){
            int val = 1;
            while(true){
                int nx = sx + dx[d]*val;
                int ny = sy + dy[d]*val;
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) break;
                if(data[nx][ny] == 6) break;

                if(data[nx][ny] == 0){
                    data[nx][ny] = -1;
                    changed.add(new int[]{nx, ny});
                }
                val++;
            }
        }
        return changed;
    }

    static void revoke(List<int[]> changed){
        for(int[] p : changed){
            data[p[0]][p[1]] = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int val = Integer.parseInt(st.nextToken());
                data[i][j] = val;
                if(val != 0 && val!= 6){
                    cctvs.add(new int[]{i,j,val});
                }
            }
        }

        dfs(0);

        System.out.println(min);
    }


}

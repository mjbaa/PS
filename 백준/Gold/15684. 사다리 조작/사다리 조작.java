import java.io.*;
import java.util.*;

public class Main {
    static int n,m,h;
    static int min = Integer.MAX_VALUE;
    static boolean[][] data;
    static int[] dx = {0,0};
    static int[] dy = {1,-1};

    static void dfs(int cnt, int sr, int sc){

        if(check()){
            min = Math.min(min,cnt);
            return;
        }

        if(cnt >= 3 || cnt >= min) return;

        if(sr > h) return;

        if(sc >= n){
            dfs(cnt, sr + 1, 1);
            return;
        }


        if(canBuild(sr,sc)){
            data[sr][sc] = true;
            dfs(cnt+1,sr,sc+2);
            data[sr][sc] = false;
        }

        dfs(cnt,sr,sc+1);
    }

    static boolean canBuild(int r, int c){ // 양 옆에 가로선 있는지
        if(data[r][c]) return false;
        if(c-1 >= 1 && data[r][c-1]) return false;
        if(c+1 <= n && data[r][c+1]) return false;
        return true;
    }
    
    static boolean check(){ // 모든 사다리가 i to i 성립 여부 확인
        for(int start=1;start<=n;start++){
            int cur = start;
            for(int r=1;r<=h;r++){
                if(data[r][cur]){
                    cur++;
                }else if(cur-1 >= 1 && data[r][cur-1]) {
                    cur--;
                }
            }
            if(cur != start) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        data = new boolean[h+1][n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            data[a][b] = true;
        }

        dfs(0,1,1);

        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);


    }


}

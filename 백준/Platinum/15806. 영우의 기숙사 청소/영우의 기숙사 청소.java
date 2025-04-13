import java.io.*;
import java.util.*;


public class Main {
    static int n,m,k,t;
    static boolean[][] data;
    static int[][] ptrs;

    static List<int[]> dusts;

    static int[] dx = {-1,-2,-2,-1,1,2,2,1};
    static int[] dy = {-2,-1,1,2,-2,-1,1,2};

    static boolean notRange(int x,int y){
        return (x<0||y<0||x>=n||y>=n);
    }

    static void grow(){
        data = new boolean[n][n]; // 이전 먼지 먼저 사라짐
        List<int[] > temp = new ArrayList<>();

        for(int[] dust : dusts){
            int x = dust[0];
            int y = dust[1];
            for(int i=0;i<8;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(notRange(nx,ny)) continue;
                if(data[nx][ny]) continue;
                data[nx][ny] = true;
                temp.add(new int[]{nx,ny});
            }
        }

        dusts = temp;

    }

    static boolean check(){
        for(int[] ptr : ptrs){
            int x = ptr[0];
            int y = ptr[1];
            if(data[x][y]) return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        dusts = new ArrayList<>();

        data = new boolean[n][n];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            dusts.add(new int[]{row,col});
            data[row][col] = true;
        }

        ptrs = new int[k][2];
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            ptrs[i][0] = row;
            ptrs[i][1] = col;
        }

        if(n <= 2 || (n == 3 && data[1][1]) ){
            System.out.println("NO");
            return;
        }

        boolean flag = false;
        if(t % 2 == 0){
            if(check()) {
                flag = true;
            }
        }else{
            grow();
            if(check()){
                flag = true;
            }
        }

        if(flag){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
import java.io.*;
import java.util.*;


/*
data -> map
    false : water
    true : ice
 */
public class Main {
    static int r,c;
    static boolean[][] data;
    static List<int[]> waters = new ArrayList<>();
    static boolean[][] visited;
    static int startX,startY,endX,endY;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[][] mdata;


    static boolean notRange(int x, int y){
        return x < 0 ||  y < 0 || x >= r || y >= c;
    }

    static void melting(int date){
        List<int[]> melted = new  ArrayList<>();

        Deque<int[]> dq = new ArrayDeque<>();
        for(int[] water : waters){
            int x = water[0];
            int y = water[1];
            dq.offer(new int[] {x,y});
            visited[x][y] = true;
        }

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            for(int f=0;f<4;f++){
                int nx = x+dx[f];
                int ny = y+dy[f];
                if(notRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;

                if(data[nx][ny]){ // ice라면
                    melted.add(new int[] {nx,ny});
                    data[nx][ny] = false; // 녹이기
                    mdata[nx][ny] = date;
                }
            }
        }

        waters = melted;
    }

    static boolean check(int val){
        boolean[][] visited = new boolean[r][c];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {startX,startY});
        visited[startX][startY] = true;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];

            for(int f=0;f<4;f++){
                int nx = x+dx[f];
                int ny = y+dy[f];
                if(notRange(nx,ny)) continue;
                if(mdata[nx][ny] > val) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                dq.offer(new int[] {nx,ny});
                if(nx == endX && ny == endY) return true;
            }
        }
        return false;
    }
    static int bnSearch(int l, int r){
        int left = l;
        int right = r;

        int result = -1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(check(mid)){
                result = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        data = new boolean[r][c];
        visited = new boolean[r][c];
        mdata = new int[r][c];

        boolean isFirst = true;

        for(int i=0;i<r;i++){
            String line = br.readLine();
            for(int j=0;j<c;j++){
                char val = line.charAt(j);
                if(val == '.'){
                    data[i][j] = false;
                    waters.add(new int[]{i,j});
                }else if(val == 'X'){
                    data[i][j] = true;
                }else{
                    data[i][j] = false;
                    waters.add(new int[]{i,j});
                    if(isFirst){
                        startX = i;
                        startY = j;
                        isFirst = false;
                    }else{
                        endX = i;
                        endY = j;
                    }
                }
            }
        }
        int cnt = 0;
        int iceSize = r*c - waters.size();
        while(iceSize>0){
            cnt++;
            melting(cnt);
            iceSize -= waters.size();
        }

        int result = bnSearch(0, cnt);

        System.out.println(result);


    }
}

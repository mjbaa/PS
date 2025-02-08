import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int[] cleaner = new int[2];//공기청정기가 있는 행1, 행2 -> 열은 무조건 0
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[][] diffusion(int r, int c){
        int[][] data = new int[r][c];

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j]==-1){
                    data[i][j]=-1;
                    continue;
                }
                data[i][j] += map[i][j];
                for(int k=0;k<4;k++){
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if(nx<0 || ny<0 || nx>=r || ny>=c) continue;
                    if(map[nx][ny]==-1)continue;
                    data[nx][ny] += map[i][j]/5;
                    data[i][j] -= map[i][j]/5;
                }
            }
        }
        return data;
    }


    static void move(){
        // 1
        for(int i = cleaner[0] - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0]; // 왼쪽
        }
        for(int i = 0; i < map[0].length - 1; i++) {
            map[0][i] = map[0][i + 1]; // 위쪽
        }
        for(int i = 0; i < cleaner[0]; i++) {
            map[i][map[0].length - 1] = map[i + 1][map[0].length - 1]; // 오른쪽
        }
        for(int i = map[0].length - 1; i > 1; i--) {
            map[cleaner[0]][i] = map[cleaner[0]][i - 1]; // 아래쪽
        }
        map[cleaner[0]][1] = 0;

        // 2
        for(int i = cleaner[1] + 1; i < map.length - 1; i++) {
            map[i][0] = map[i + 1][0]; // 왼쪽
        }
        for(int i = 0; i < map[0].length - 1; i++) {
            map[map.length - 1][i] = map[map.length - 1][i + 1]; // 아래쪽
        }
        for(int i = map.length - 1; i > cleaner[1]; i--) {
            map[i][map[0].length - 1] = map[i - 1][map[0].length - 1]; // 오른쪽
        }
        for(int i = map[0].length - 1; i > 1; i--) {
            map[cleaner[1]][i] = map[cleaner[1]][i - 1]; // 위쪽
        }
        map[cleaner[1]][1] = 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<c;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<r;i++){
            if (map[i][0] == -1) {
                cleaner[0] = i;
                cleaner[1] = i+1;
                break;
            }
        }

        while(t-- > 0){
            map = diffusion(r,c);
            move();
        }


        int sum = 0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                sum += map[i][j];
            }
        }
        System.out.println(sum+2);
    }
}

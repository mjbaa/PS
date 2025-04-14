
import java.io.*;
import java.util.*;

public class Main {
    static int n,m,p;
    static int[][] data;
    static int[] range;
    static List<int[]> castles[];

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static boolean expanded;

    static boolean notRange(int x, int y){
        return (x<0 || y < 0 || x >= n || y>= m);
    }

//    static void bfs(int idx, List<int[]> castles){
//        List<int[]> temp = new ArrayList<>();
//
//        for(int[] castle : castles){
//            Queue<int[]> que = new LinkedList<>();
//
//            int i = castle[0];
//            int j = castle[1];
//
//            que.add(new int[] {i,j, 0});
//
//            while(!que.isEmpty()){
//                int[] cur = que.poll();
//                int x = cur[0];
//                int y = cur[1];
//                int cnt = cur[2];
//
//                if(cnt >= range[idx]) continue;
//
//                for(int f=0;f<4;f++){
//                    int nx = x + dx[f];
//                    int ny = y + dy[f];
//
//                    if(notRange(nx, ny)) continue;
//                    if(data[nx][ny] != 0 && data[nx][ny] != idx) continue;
//
//                    expanded = true;
//                    que.add(new int[] {nx,ny, cnt+1});
//                    if(data[nx][ny] != idx){
//                        temp.add(new int[] {nx,ny});
//                        data[nx][ny] = idx;
//                    }
//                }
//            }
//        }
//
////        for(int[] nc : temp){
////            castles.add(nc);
////            data[nc[0]][nc[1]] = idx;
////        }
//        castles.addAll(temp);
//    }

    static void bfs(int idx, List<int[]> castles) {
        Queue<int[]> que = new LinkedList<>(castles);

        List<int[]> newCastleList = new ArrayList<>(); // 새로 얻은 칸들

        int move = 0;
        while (move < range[idx] && !que.isEmpty()) {
            int size = que.size();
            for (int s = 0; s < size; s++) {
                int[] cur = que.poll();
                int x = cur[0];
                int y = cur[1];
                for (int f = 0; f < 4; f++) {
                    int nx = x + dx[f];
                    int ny = y + dy[f];
                    if (notRange(nx, ny)) continue;
                    if (data[nx][ny] != 0) continue;

                    expanded = true;
                    data[nx][ny] = idx;
                    newCastleList.add(new int[]{nx, ny});
                    que.add(new int[]{nx, ny});
                }
            }
            move++;
        }

        // castles를 새로 얻은 칸들로 갱신
        castles.clear();
        castles.addAll(newCastleList);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        castles = new ArrayList[p+1];
        for(int i = 1; i <=p; i++){
            castles[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        range = new int[p+1];
        for(int i=1;i<=p;i++){
            range[i] = Integer.parseInt(st.nextToken());
        }

        data = new int[n][m];
        for(int i=0;i<n;i++){
            String line = br.readLine();
            for(int j=0;j<m;j++){
                if(line.charAt(j)=='.') data[i][j] = 0;//빈 공간
                else if(line.charAt(j)=='#') data[i][j] = -1;//벽
                else {
                    int val = line.charAt(j)-'0';
                    data[i][j] = val;
                    castles[val].add(new int[] {i,j});
                }

            }
        }

        expanded = true;

        while(expanded){
            expanded = false;

            for(int i=1;i<=p;i++){
                bfs(i, castles[i]); // 하나라도 확장? -> expanded : true
            }


        }

        /*
        StringBuilder sb = new StringBuilder();


        for(int i=1;i<=p;i++){
            List<int[]> castle = castles[i];
            if(castle == null) continue;

            if(i != castles.length-1) sb.append(castle.size()).append(" ");
            else sb.append(castle.size());
        }
        System.out.println(sb);

        */

        int[] result = new int[p + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int owner = data[i][j];
                if (owner >= 1 && owner <= p) {
                    result[owner]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= p; i++) {
            sb.append(result[i]);
            if (i != p) sb.append(" ");
        }
        System.out.println(sb);

    }
}




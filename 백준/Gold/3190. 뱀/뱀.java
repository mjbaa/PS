import java.io.*;
import java.util.*;

public class Main {
    static int n,k,l;
    static int[][] board;
    
    //우하좌상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int dirIdx = 0;

    static class Point{
        int x,y;
        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    static class DirChange {
        int time;
        char c;
        DirChange(int time, char c) {
            this.time = time;
            this.c = c;
        }
    }

    static Deque<Point> snake = new LinkedList<>();
    static Queue<DirChange>  dirChanges = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];

        k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = -1; //사과
        }

        l = Integer.parseInt(br.readLine());
        for(int i = 0; i < l; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            dirChanges.add(new DirChange(t, c));
        }

        board[1][1] = 1;
        dirIdx = 0;

        int time = 0;
        snake.addLast(new Point(1, 1));

        while(true){
            time++;
            Point head = snake.peek();
            int nx = head.x + dx[dirIdx];
            int ny = head.y + dy[dirIdx];

            //벽
            if(nx < 1 || ny < 1 || nx > n || ny > n) break;

            //몸통
            if(board[nx][ny] == 1) break;

            if(board[nx][ny] == -1) { //사과
                Point newHead = new Point(nx, ny);
                snake.offerFirst(newHead);
                board[nx][ny] = 1;
            }else{ //빈 칸
                Point newHead = new Point(nx, ny);
                snake.addFirst(newHead);
                board[nx][ny] = 1;

                Point tail = snake.pollLast();
                board[tail.x][tail.y] = 0;
            }

            if(!dirChanges.isEmpty() && dirChanges.peek().time == time){
                DirChange change = dirChanges.poll();
                char c = change.c;
                if(c == 'L'){ // 왼쪽
                    dirIdx = (dirIdx + 3) % 4;
                }else if(c == 'D'){ // 오른쪽
                    dirIdx = (dirIdx + 1) % 4;
                }
            }

        }

        System.out.println(time);

    }


}

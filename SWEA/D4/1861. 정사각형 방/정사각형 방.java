import java.io.*;
import java.util.*;
 
public class Solution {
    static int n,m;
    static int[][] arr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
 
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            m=n*n;
            visited=new boolean[m+1];
 
 
            // 숫자 i의 위치를 담는 배열
            // ex) 숫자 1이 (2,3) 에 존재한다면 nums[1]=(2,3) 이 들어감
            Point[] nums=new Point[m+1];
 
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    nums[arr[i][j]] = new Point(i, j);
                }
            }
 
 
            int answer=0,max=0;
            for (int i = 1; i <=m; i++) {
                if(visited[i])
                    continue;
                int cnt = move(nums[i]);
                if (cnt > max) {
                    answer= i;
                    max=cnt;
                }
            }
            sb.append("#").append(t).append(" ").append(answer)
                    .append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
 
    static int move(Point start) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0 ,1, -1};
        int cnt=0;
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        visited[m]=true;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            cnt++;
 
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<0 || nx>=n || ny<0 || ny>=n || arr[nx][ny]!=arr[cur.x][cur.y]+1)
                    continue;
                q.add(new Point(nx, ny));
                visited[arr[nx][ny]]=true;
            }
        }
        return cnt;
    }
}
 
class Point{
    int x,y;
 
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
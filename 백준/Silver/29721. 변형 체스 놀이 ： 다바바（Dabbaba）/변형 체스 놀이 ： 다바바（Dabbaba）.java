import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    static int[] dx = {0,0,-2,2};
    static int[] dy = {-2,2,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();

        Set<String> pre = new HashSet<>();

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new int[]{a,b});
            pre.add(a+","+b);
        }

        Set<String> set = new HashSet<>();

        for(int i=0;i<k;i++){
            int[] cur = list.get(i);
            int x = cur[0];
            int y = cur[1];
            for(int j=0;j<4;j++){
                int nx = x + dx[j];
                int ny = y + dy[j];
                if(nx<=0 || ny<=0 || nx>n || ny>n) continue;
                if(pre.contains(nx+","+ny)) continue;
                set.add(nx+","+ny);
            }
        }

        System.out.println(set.size());
    }
}

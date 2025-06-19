import java.util.*;
import java.io.*;

public class Main {
    static int n,m, tc, wc;
    static List<int[]> wrongs = new ArrayList<>();


    static boolean check(int size) {
        int cnt = 0;
        int prevCoveredY = 0;

        for (int[] cell : wrongs) {
            int x = cell[0];
            int y = cell[1];

            if (x > size) return false;

            if (prevCoveredY < y) {
                cnt++;
                prevCoveredY = y + size - 1;

                if (cnt > tc) return false;
            }
        }

        return true;
    }


    static int bnSearch(int left, int right){
        int answer = 0;

        while(left <= right){
            int mid = left + (right - left)/2;
            if(check(mid)){
                answer = mid;
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tc = Integer.parseInt(br.readLine());
        wc = Integer.parseInt(br.readLine());

        for(int i = 0; i < wc; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            wrongs.add(new int[]{row,col});
        }

        Collections.sort(wrongs, (a,b) -> a[1] - b[1]);

        int result = bnSearch(1,Math.min(n,m));

        System.out.println(result);

    }
}

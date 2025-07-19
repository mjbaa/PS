import java.util.*;
import java.io.*;

public class Main {
    static int n,m,l;
    static List<Integer> Places;

    static int bnSearch(int x){
        int left = 0;
        int right = m-1;

        int min = Integer.MAX_VALUE;
        int gap = 0;

        while(left <= right){
            int mid = left + (right - left)/2;
            int val = Places.get(mid);

            gap = Math.abs(val-x);
            if(val < x){
                left = mid + 1;
            }else{
                right = mid - 1;
            }

            min = Math.min(min, gap);
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        Places = new ArrayList<>(m);
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            Places.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(Places);

        int cnt = 0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int xGap = bnSearch(x);

            if(xGap + y <= l) cnt++;

        }
        System.out.println(cnt);



    }
}

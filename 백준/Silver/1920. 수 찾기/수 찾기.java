import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static List<Integer> data;


    static boolean bnSearch(int target){
        int left = 0;
        int right = n-1;

        while(left <= right){
            int mid = (left + right)/2;

            int cur = data.get(mid);

            if(cur == target) return true;
            else if(cur < target) left = mid + 1;
            else right = mid - 1;

        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        data = new ArrayList<>(n);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            data.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(data);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());

            if(bnSearch(target)) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb);

    }
}

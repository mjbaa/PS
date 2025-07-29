import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> data;

    static int a, b;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new ArrayList<>(n);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            data.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(data);
        for(int i = 0; i < n - 1; i++){
            int val = data.get(i);
            int left = i + 1;
            int right = n - 1;

            while(left <= right){
                int mid = (left + right) / 2;
                int sum = val + data.get(mid);

                if(Math.abs(sum) < minDiff){
                    minDiff = Math.abs(sum);
                    a = val;
                    b = data.get(mid);
                }

                if(sum < 0) left = mid + 1;
                else right = mid - 1;
            }
        }

        System.out.println(a + " " + b);
        

    }
}

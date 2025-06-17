import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] data;
    static List<Integer> twoSum = new ArrayList<>();

    static boolean bnSearch(int target){
        int left = 0;
        int right = twoSum.size() - 1;

        while(left<=right){
            int mid = left + (right-left)/2;
            int cur = twoSum.get(mid);

            if(cur == target) return true;
            else if(cur < target) left = mid + 1;
            else right = mid-1;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(data);

        for(int i = 0; i < n-1; i++){
            for(int j = i; j < n; j++){
                twoSum.add(data[i]+data[j]);
            }
        }
        Collections.sort(twoSum);

        for(int i = n-1; i>=0;i--){
            for(int j=0;j<i;j++){
                int target = data[i]-data[j];

                if(bnSearch(target)){
                    System.out.println(target + data[j]);
                    return;
                }
            }
        }



    }
}

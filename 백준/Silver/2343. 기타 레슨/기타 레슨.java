import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[] data;


    static boolean check(int size){ // 블루레이 크기
        int cnt = 1;

        int curSize = 0;
        for(int i=0;i<n;i++){
            if(curSize + data[i] > size){
                cnt++;
                curSize = data[i];
            }else{
                curSize += data[i];
            }
        }

        return cnt <= m;

    }
    static int bnSearch(int left, int right){

        int answer = 0;
        while(left <= right){
            int mid = left + (right - left)/2;

            if(check(mid)){
                answer = mid;
                right = mid -1;
            }else{
                left = mid+1;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new int[n];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            data[i] = val;

            sum += val;
            max = Math.max(max, val);
        }

        int result = bnSearch(max,sum);

        System.out.println(result);

    }
}

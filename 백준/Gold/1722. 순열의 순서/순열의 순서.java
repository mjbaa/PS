import java.util.*;
import java.io.*;


public class Main {



    static long facto(int val) {
        long sum = 1;
        for(long i=1;i<=val;i++) {
            sum = sum*i;
        }
        return sum;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Integer> nums = new LinkedList<>();

        int n = Integer.parseInt(br.readLine());

        for(int i=1;i<=n;i++) {
            nums.add(i);
        }

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        if(t == 1) {
            long k=Long.parseLong(st.nextToken())-1;

            StringBuilder result = new StringBuilder();
            for(int i=n;i>0;i--) {
                long idx = k / facto(i-1);
                result.append(nums.get((int) idx)).append(' ');

                k %= facto(i-1);
                nums.remove((int) idx);
            }
            System.out.println(result.toString());
        }else {
            int[] data = new int[n];
            for(int i=0;i<n;i++) {
                data[i] = Integer.parseInt(st.nextToken());
            }

            long k=0;
            for(int i=0;i<n;i++) { // a 의 index : a-1
                long index = nums.indexOf(data[i]); // index : data[i]보다 작은 수의 개수
//                nums.remove(index);
                nums.remove((Integer) data[i]);

                k += index * facto(n-i-1);
                //System.out.println("i : "+i+ " val : "+index * facto(n-i-1));
            }
            System.out.println(k+1);
        }
    }
}
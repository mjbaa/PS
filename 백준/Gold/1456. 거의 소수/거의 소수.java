import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isNotPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int sqrtB = (int)Math.sqrt(B);
        isNotPrime = new boolean[sqrtB + 1];


        for (int i = 2; i <= Math.sqrt(sqrtB); i++) {
            if (isNotPrime[i]) continue;
            for (int j = i * i; j <= sqrtB; j += i) {
                isNotPrime[j] = true;
            }
        }

        int count = 0;


        for (int i = 2; i <= sqrtB; i++) {
            if (isNotPrime[i]) continue;

            long val = i;
            while ((double)val * i <= B) { 
                val *= i;
                if (val >= A) count++;
            }
        }

        System.out.println(count);
    }
}

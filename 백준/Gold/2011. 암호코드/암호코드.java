import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
 
        long dp[] = new long[str.length() + 1];
        dp[0] = 1;
 
        for (int i = 1; i <= str.length(); i++) {
            int cur = str.charAt(i-1) - '0';
            if (cur >= 1 && cur <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= 1000000;
            }
 
            if(i == 1) continue;
 
            int pre = str.charAt(i - 2) - '0';
 
            if(pre == 0) continue;
 
            int ten = pre * 10 + cur;
 
            if (ten <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= 1000000;
            }
        }
        System.out.println(dp[str.length()]);
    }
}
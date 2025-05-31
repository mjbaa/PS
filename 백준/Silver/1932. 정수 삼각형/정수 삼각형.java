import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] data = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()) {
                data[i][idx] = Integer.parseInt(st.nextToken());
                idx++;
            }
        }

        dp[0][0] = data[0][0];
        for(int i=1;i<n;i++){
            for(int j=0;j<=i;j++){
                //dp[i][j] : 바로 위, 바로 위의 왼쪽 비교 필요
                dp[i][j] = data[i][j];

                if(j>0){//왼쪽 았움
                    dp[i][j] += Math.max(dp[i-1][j-1], dp[i-1][j]);

                }else{//왼쪽 없음
                    dp[i][j] += dp[i-1][j];
                }
            }
        }

        int max = 0;
        for(int i=0;i<n;i++) {
            max = Math.max(max, dp[n - 1][i]);
        }

        System.out.println(max);


    }
}


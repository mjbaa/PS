import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);

        int[][] item = new int[N + 1][2];  // weight, value

        for (int i = 1; i <= N; i++) {
            inputs = br.readLine().split(" ");
            item[i][0] = Integer.parseInt(inputs[0]);
            item[i][1] = Integer.parseInt(inputs[1]);
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int w = 1; w <= K; w++) { // 무게
            for (int i = 1; i <= N; i++) { // item
                dp[i][w] = dp[i - 1][w];

                if (w - item[i][0] >= 0) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w], // i번 물건 안넣고 무게 최대 w 안의 최적
                            item[i][1] + dp[i - 1][w - item[i][0]]); // i번 물건 넣고 무게 w내 최적
                }
            }
        }


        System.out.println(dp[N][K]);
    }
}
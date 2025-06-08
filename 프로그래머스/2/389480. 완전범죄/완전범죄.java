import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int k = info.length;

        int[][] dp = new int[k + 1][m];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int i = 0; i < k; i++) {
            int aCost = info[i][0];
            int bCost = info[i][1];

            for (int b = 0; b < m; b++) {
                if (dp[i][b] == Integer.MAX_VALUE) continue;

                if (dp[i][b] + aCost < n) {
                    dp[i + 1][b] = Math.min(dp[i + 1][b], dp[i][b] + aCost);
                }

                if (b + bCost < m) {
                    dp[i + 1][b + bCost] = Math.min(dp[i + 1][b + bCost], dp[i][b]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int b = 0; b < m; b++) {
            if (dp[k][b] < n) {
                result = Math.min(result, dp[k][b]);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

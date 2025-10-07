import java.io.*;
import java.util.*;

class Solution {
    boolean[][] data;
    int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        data = new boolean[n + 1][m + 1];
        dp = new int[n + 1][m + 1];

        for (int[] p : puddles) {
            int x = p[0], y = p[1];
            data[y][x] = true;
        }

        for (int x = 1; x <= m; x++) {
            if (data[1][x]) break;
            dp[1][x] = 1;
        }

        for (int y = 1; y <= n; y++) {
            if (data[y][1]) break;
            dp[y][1] = 1;
        }

        for (int y = 2; y <= n; y++) {
            for (int x = 2; x <= m; x++) {
                if (!data[y][x]) {
                    dp[y][x] = ( (dp[y-1][x] + dp[y][x-1]) % 1_000_000_007 );
                }
            }
        }

        return dp[n][m];
    }
}
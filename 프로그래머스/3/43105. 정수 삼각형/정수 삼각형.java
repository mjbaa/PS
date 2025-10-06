import java.io.*;
import java.util.*;


/*
각 칸 : (행 + 1, 열 = +0 / +1 )로만 이동 가능
i 번 행 : i+1 개 열 존재 
*/
class Solution {
    int h;
    int[][] data;
    int[][] dp;
    

    public int solution(int[][] triangle) {
        h = triangle.length;
        data = triangle;
        dp = new int[h][h]; // r,c에 대해서 root ~ (r,c) 까지 최대 경로 값
        dp[0][0] = data[0][0];
        
        for(int i=0;i<h-1;i++){
            for(int j=0;j<=i;j++){
                int cur = dp[i][j];
                dp[i+1][j] = Math.max(data[i+1][j] + cur, dp[i+1][j]);
                dp[i+1][j+1] = Math.max(data[i+1][j+1] + cur, dp[i+1][j+1]);
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int j = 0; j < h;j++){
            max = Math.max(max, dp[h-1][j]);
        }
        return max;
    }
}
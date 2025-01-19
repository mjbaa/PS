
import java.util.*;
import java.io.*;
 
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int maxScore;
    static void dfs(int[] scores, int[] calories, int index, int currentScore, int currentCalorie, int limit) {
        if (currentCalorie > limit) return;
        if (index == scores.length) {
            maxScore = Math.max(maxScore, currentScore);
            return;
        }
        dfs(scores, calories, index + 1, currentScore, currentCalorie, limit);
        dfs(scores, calories, index + 1, currentScore + scores[index], currentCalorie + calories[index], limit);
    }
     
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
 
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 재료의 수
            int L = Integer.parseInt(st.nextToken()); // 제한 칼로리
 
            int[] scores = new int[N];
            int[] calories = new int[N];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }
 
            maxScore = 0;
            dfs(scores, calories, 0, 0, 0, L);
 
            System.out.println("#" + t + " " + maxScore);
        }
         
         
    }
}
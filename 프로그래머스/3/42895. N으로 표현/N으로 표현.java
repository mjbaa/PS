import java.util.*;

class Solution {
    
    public int solution(int N, int number) {
        Set<Integer> dp[] = new Set[9]; // dp[i] : i번 사용해서 만들 수 있는 숫자
        for(int i=1;i<9;i++){
            dp[i] = new HashSet<Integer>();
        }
        
        dp[1].add(N);
        
        for(int i = 2; i<= 8; i++){
            for(int j=1;j<i;j++){
                
                int val = N;
                for(int f = 2;f<=i;f++){
                    val = 10*val + N;
                }
                dp[i].add(val);
                
                Set<Integer> setA = dp[j];
                Set<Integer> setB = dp[i-j];
                
                for(Integer a : setA){
                    for(Integer b : setB){
                        dp[i].add(a+b);
                        dp[i].add(a-b);
                        dp[i].add(a*b);
                        if(b != 0 ) dp[i].add(a/b);
                    }
                }
                
            }
        }
        int answer = -1;
        for(int i=1;i<=8;i++){
            if(dp[i].contains(number)){
                answer = i;
                return answer;
            }
        }
        
        return answer;
    }
}
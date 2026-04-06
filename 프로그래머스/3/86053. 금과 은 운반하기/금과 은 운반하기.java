import java.io.*;
import java.util.*;

class Solution {
    int n;
    long min = Long.MAX_VALUE;
    int A,B;
    int[] G, S, W, T;
    
    boolean check(long mid){
        long sumA = 0l;
        long sumB = 0l;
        long sumAB = 0l;
        for(int i=0;i<n;i++){
            long cnt = mid / (2L * T[i]);
            if(mid % (2L * T[i]) >= T[i]) cnt++;

            long maxMove = cnt * W[i];

            long g = Math.min((long) G[i], maxMove);
            long s = Math.min((long) S[i], maxMove);
            long both = Math.min((long) G[i] + S[i], maxMove);

            sumA += g;
            sumB += s;
            sumAB += both;
        }
        
        return sumA >= A && sumB >= B && sumAB >= A+B;
    }
    
    void bnSearch(long left, long right){
        while(left <= right){
            long mid = left + (right - left) / 2;
        
            if(check(mid)){
                min = mid;
                right = mid - 1l;
            }else{
                left = mid + 1l;
            }
        }
    }
    
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        n = g.length;
        A=a;
        B=b;
        G=g;
        S=s;
        W=w;
        T=t;
        
        long left = 0l;
        long right = 400_000_000_000_000L;    
        
        bnSearch(left, right);
        
        
        return min;
    }
}
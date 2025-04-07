import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] line1 = br.readLine().toCharArray();
        char[] line2 = br.readLine().toCharArray();
        
        int[][] dp = new int[line1.length+1][line2.length+1];
        
        for(int i=1;i<=line1.length;i++) {
        	for(int j=1;j<=line2.length;j++) {
        		if(line1[i-1] == line2[j-1]) {
        			dp[i][j] = dp[i-1][j-1]+1;
        		}else {
        			dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        		}
        	}
        }
        
        System.out.println(dp[line1.length][line2.length]);

        
        int i = line1.length;
        int j = line2.length;
        while(i > 0 && j > 0) {
        	if(line1[i-1] == line2[j-1]) {
        		sb.append(line1[i-1]);
        		i--;
        		j--;
        	}else {
        		if(dp[i-1][j] > dp[i][j-1]) {
        			i--;
        		}else {
        			j--;
        		}
        	}
        }
        
        System.out.println(sb.reverse());
        
        
    }
}

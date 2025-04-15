import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int left = 0;
        int right = k;
        int result = 0;
        
        while(left <= right) {
        	int mid = (left+right) / 2;
        	
        	long count = 0;
        	for(int i=1;i<=n;i++) {
        		count += Math.min(mid/i, n); //작거나 같은 수
        	}
        	
        	if (k <= count) { // 작거나 같은 수를 체크했기 떄문에 count == k 인 수들 중 가장 작은 값이 정답
        	    result = mid;
        	    right = mid - 1;
        	} else {
        	    left = mid + 1;
        	}

        }
        
        System.out.println(result);
    }
}

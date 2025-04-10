import java.io.*;
import java.util.*;

public class Main {
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,  true);
        
        isPrime[0] = false;
        isPrime[1] = false;
        
        for(int i=2;i*i<=n;i++){
        		if(isPrime[i]) {
        			for(int j=i*i;j<=n;j+=i) {
        				isPrime[j] = false;
        			}
        		}
        }
        
        List<Integer> primes = new ArrayList<>();
        for(int i=2;i<=n;i++) {
        	if(isPrime[i]) {
        		primes.add(i);
        	}
        }
        
        
        int cnt = 0;
        int sum = 0;
        int start = 0;
        int end = 0;
        
        while (true) {
            if (sum >= n) {
                sum -= primes.get(start++);
            } else if (end == primes.size()) {
                break;
            } else {
                sum += primes.get(end++);
            }
            
            if (sum == n) {
            	cnt++;
            }
        }
        
        System.out.println(cnt);
    }
}

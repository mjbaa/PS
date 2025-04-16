import java.io.*;
import java.util.*;

public class Main {
	static int target,m,n;
    static int[] sliceA;
    static int[] sliceB;
	
    static Map<Integer, Integer> sumA = new HashMap<>();
    static Map<Integer, Integer> sumB = new HashMap<>();
    

    
    static void makeMap() {
        int N = sliceA.length;

        
        for (int len = 1; len <= N; len++) {
            int sum = 0;

            
            for (int i = 0; i < len; i++) {
                sum += sliceA[i];
            }
            sumA.put(sum, sumA.getOrDefault(sum, 0) + 1);

            
            for (int i = 1; i < N; i++) {
                sum -= sliceA[i - 1];
                sum += sliceA[(i + len - 1) % N];

                if (len == N && i != 0) break; 

                sumA.put(sum, sumA.getOrDefault(sum, 0) + 1);
            }
        }
        
        
        N = sliceB.length;
        
        for (int len = 1; len <= N; len++) {
            int sum = 0;

            
            for (int i = 0; i < len; i++) {
                sum += sliceB[i];
            }
            sumB.put(sum, sumB.getOrDefault(sum, 0) + 1);

            
            for (int i = 1; i < N; i++) {
                sum -= sliceB[i - 1];
                sum += sliceB[(i + len - 1) % N];

                if (len == N && i != 0) break; 

                sumB.put(sum, sumB.getOrDefault(sum, 0) + 1);
            }
        }
        
    }

    
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	target = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	
    	sliceA = new int[m];
    	for(int i=0;i<m;i++) {
    		int val = Integer.parseInt(br.readLine());
    		sliceA[i] = val;
    	}

    	

    	sliceB = new int[n];
    	for(int i=0;i<n;i++) {
    		int val = Integer.parseInt(br.readLine());
    		sliceB[i] = val;

    	}

    	
    	makeMap();
    	
    	int totalCnt = 0;
    	Set<Integer> Akeys = sumA.keySet();
    	for(int aKey : Akeys) {
    		int aCnt = sumA.get(aKey);
    		
    		int targetKey = target - aKey;
    		
    		if(sumB.containsKey(targetKey)) {
    			int bCnt = sumB.get(targetKey);
    			totalCnt += aCnt * bCnt;
    		}
    	}
    	

    	totalCnt += sumA.getOrDefault(target, 0); 
    	totalCnt += sumB.getOrDefault(target, 0); 

    	
    	
    	System.out.print(totalCnt);
    	
    	
    }
    
}

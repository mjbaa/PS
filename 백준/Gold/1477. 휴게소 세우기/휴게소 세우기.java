import java.io.*;
import java.util.*;

public class Main {
	static int N,M,L;
	static int[] data;
	
	static int bns(int min, int max) {
		int left = min;
		int right = max;
		int result = 0;
		
		while(left <= right) {
			int mid = (left + right)/2;
			if(check(mid)) {
				result = mid;
				right = mid-1;
			}else {
				left = mid + 1;
			}
		}
		
		return result;
	}
	
	static boolean check(int dist) {
        int cnt = 0;
        for (int i = 1; i < data.length; i++) {
            int gap = data[i] - data[i - 1];
            cnt += (gap - 1) / dist;
        }
        return cnt <= M;
    }
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		data = new int[N+2];
		st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=N;i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		
		data[0] = 0;
        data[N + 1] = L;
        Arrays.sort(data);
		int min = 1;
		int max = L;
		
		int result = bns(min,max);
		
		System.out.print(result);
	}
}

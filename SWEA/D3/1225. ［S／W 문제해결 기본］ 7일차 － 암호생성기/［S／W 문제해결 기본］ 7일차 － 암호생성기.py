
import java.io.*;
import java.util.*;

public class Solution {
	static Deque<Integer> dq = new ArrayDeque(16);
	
	static void func() {
		int minVal = Collections.min(dq);
        
        // 최소값을 이용해 주기성을 활용
        int cycle = minVal / 15;  // (1+2+3+4+5) = 15, 최소값을 기준으로 몇 번 반복 가능한지
        for (int i = 0; i < 8; i++) {
            dq.add(dq.poll() - (cycle * 15)+15);
        }
        
		while(true) {
			for(int i=1;i<=5;i++) {
				int val = dq.poll();
				if(val <= i) {
					val = 0;
					dq.add(val);
					return;
				}else {
					val -= i;
					dq.add(val);
				}
			}
		}
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int test_case = 1;test_case <=10;test_case ++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for(int i=0;i<8;i++) {
				dq.add(Integer.parseInt(st.nextToken()));
			}

			
			func();
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case);
			for(int i=0;i<8;i++) {
				sb.append(" ").append(dq.poll());
			}
			
			System.out.println(sb);
			
			dq.clear();
			
		}
	}

}


//1 2 3 4 5 1 2 3
//4 5 1 2 3 4 5 1
//2 3 4 5 1 2 3 4
//5 1 2 3 4 5 1 2
//3 4 5 1 2 3 4 5
//--
//1 2 3 4 5 1 2 3
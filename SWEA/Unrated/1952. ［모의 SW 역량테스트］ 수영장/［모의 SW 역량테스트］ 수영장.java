
import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] monthUses = new int[13]; // 1-based index
	static int[] costs = new int[4];//1일, 1달, 3달, 1년
	static int min;
	
	static void dfs(int month, int cost) {
		if(cost > min) return;
		if(month >12) {
			min = Math.min(min, cost);
			return;
		}
		if(monthUses[month] == 0){//이용X달인 경우
			dfs(month+1, cost);
		}
		dfs(month+1, cost + costs[0] * monthUses[month]);
		dfs(month+1, cost + costs[1]);
		dfs(month+3, cost + costs[2]);

	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int test_case = 1;test_case <= t;test_case++) {
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			min = costs[3]; // default : 1년 이용권
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<12;i++) {
				monthUses[i+1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(1,0);
			
			sb.append("#").append(test_case).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
		
	}
}

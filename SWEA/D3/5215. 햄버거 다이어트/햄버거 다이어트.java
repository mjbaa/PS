

import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int L;
	static int score;
	static int[][] foods;
	
	
	static void dfs(int index, int cal, int sc) {
		if(index == N) {
			if(L < cal) return;
			if(score < sc) {
				score = sc;
			}
			
			return;
		}
		
		dfs(index+1, cal,sc);
		dfs(index+1, cal + foods[index][1], sc + foods[index][0]);
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= t; test_case++) {
			score = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			foods = new int[N][2];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				foods[i][0] = Integer.parseInt(st.nextToken());
				foods[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0,0);
			
			
			System.out.println("#"+test_case+" "+score);
			
		}

	}

}

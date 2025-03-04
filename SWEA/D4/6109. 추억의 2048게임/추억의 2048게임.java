

import java.io.*;
import java.util.*;

public class Solution {

	static int n;
	static int[][] data;
	
	
	public static void main(String[] args) throws Exception{

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			data = new int[n][n];
			
			String cmd = st.nextToken();
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (cmd.equals("up")) {
			    for (int j = 0; j < n; j++) {
			        int[] temp = new int[n];
			        int idx = 0;
			        
			        for(int i=0;i<n;i++) {
			        	if(data[i][j] != 0) {
			        		temp[idx] = data[i][j];
			        		idx++;
			        	}
			        }//temp에 0을 제외한 수 넣기
			        
			        
			        for(int i=0;i<n-1;i++) {
			        	if(temp[i] != 0 && temp[i] == temp[i+1]) {
			        		temp[i] *= 2;
			        		temp[i+1] = 0;
			        	}
			        }//temp 내에서 합치기
			        
			        idx = 0;
			        for(int i=0; i<n;i++) {
			        	if(temp[i] != 0) {
			        		data[idx][j] = temp[i];
			        		idx++;
			        	}
			        	
			        }//temp에서 0 제외한 수를 순서대로 data에 넣기
			        
			        for(int i = idx; i<n;i++) {
			        	data[i][j] = 0;
			        }//0 제외한 수 != n개 -> 나머지 자리들은 0
			        
			    }
			}else if(cmd.equals("down")) {
				for (int j = 0; j < n; j++) {
			        int[] temp = new int[n];
			        int idx = n-1;
			        
			        for(int i=n-1;i>=0;i--) {
			        	if(data[i][j] != 0) {
			        		temp[idx] = data[i][j];
			        		idx--;
			        	}
			        }//temp에 0을 제외한 수 넣기
			        
			        
			        for(int i=n-1;i>=1;i--) {
			        	if(temp[i] != 0 && temp[i] == temp[i-1]) {
			        		temp[i] *= 2;
			        		temp[i-1] = 0;
			        	}
			        }//temp 내에서 합치기
			        
			        idx = n-1;
			        for(int i=n-1; i>=0;i--) {
			        	if(temp[i] != 0) {
			        		data[idx][j] = temp[i];
			        		idx--;
			        	}
			        	
			        }//temp를 data에 넣기
			        
			        for(int i = idx; i>=0;i--) {
			        	data[i][j] = 0;
			        }
			        
			    }
			}else if(cmd.equals("left")) {
				for(int i=0;i<n;i++) {
					int[] temp = new int[n];
					
					int idx = 0;
					for(int j=0;j<n;j++) {
						if(data[i][j] != 0) {
							temp[idx] = data[i][j];
							idx++;
						}
					}
					
					for(int j=0;j<n-1;j++) {
						if(temp[j] != 0 && temp[j] == temp[j+1]) {
							temp[j] *= 2;
							temp[j+1] = 0;
						}
					}
					
					idx = 0;
					for(int j=0;j<n;j++) {
						if(temp[j] != 0) {
							data[i][idx] = temp[j];
							idx ++;
						}
					}
					
					for(int j=idx;j<n;j++) {
						data[i][j] = 0;
					}
					
				}
			}else {
				for(int i=0;i<n;i++) {
					int[] temp = new int[n];
					
					int idx = n-1;
					for(int j=n-1;j>=0;j--) {
						if(data[i][j] != 0) {
							temp[idx] = data[i][j];
							idx--;
						}
					}
					
					for(int j=n-1;j>=1;j--) {
						if(temp[j] != 0 && temp[j] == temp[j-1]) {
							temp[j] *= 2;
							temp[j-1] = 0;
						}
					}
					
					idx = n-1;
					for(int j=n-1;j>=0;j--) {
						if(temp[j] != 0) {
							data[i][idx] = temp[j];
							idx --;
						}
					}
					
					for(int j=idx;j>=0;j--) {
						data[i][j] = 0;
					}
					
				}
			}

			sb.append("#").append(test_case).append("\n");
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sb.append(data[i][j]).append(" ");
				}
				sb.append("\n");
				
			}
			
			
		}
		System.out.println(sb);
	}

}

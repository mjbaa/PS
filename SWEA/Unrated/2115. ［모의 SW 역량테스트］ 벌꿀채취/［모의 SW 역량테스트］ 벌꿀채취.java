import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static int n, m, c;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;

            // 첫 번째 일꾼
            for (int i1 = 0; i1 < n; i1++) {
                for (int j1 = 0; j1 <= n - m; j1++) {
                	max = 0;
                	dfs(i1,j1,0,0,0);
                    int first = max;

                    // 두 번째 일꾼
                    for (int i2 = i1; i2 < n; i2++) {
                    	
                    	int j;
                    	if(i1 == i2) j = j1+m;
                    	else j = 0;
                    	
                    	
                        for (int j2 = j; j2 <= n - m; j2++) {
                        	max = 0;
                        	dfs(i2,j2,0,0,0);
                            int second = max;
                            
                            result = Math.max(result, first + second);
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    static int get(int i, int j) {
        max = 0;
        dfs(i, j, 0, 0, 0);
        return max;
    }

    static void dfs(int i, int j, int cnt, int sum, int total) {
        if (sum > c) return;
        if (cnt == m) {
        	max = Math.max(max, total);
            return;
        }

        int val = map[i][j + cnt];

        //포함
        dfs(i, j, cnt + 1, sum + val, total + val * val);

        //미포함
        dfs(i, j, cnt + 1, sum, total);
    }
}

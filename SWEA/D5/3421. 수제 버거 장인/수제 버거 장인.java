
import java.util.*;
import java.io.*;

public class Solution {
    static int n, cnt;
    static boolean[] visited;
    static List<int[]> restrictions;

    static void dfs(int index) {
        if (index > n) { 
            
        	for (int[] r : restrictions) {
                if (visited[r[0]] && visited[r[1]]) {
                    return; 
                }
            }
            cnt++; 
            return;
        }
    
        dfs(index + 1);

        visited[index] = true;
        dfs(index + 1);
        visited[index] = false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            restrictions = new ArrayList<>();
            visited = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                restrictions.add(new int[]{a, b});
            }

            cnt = 0;
            dfs(1); 
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}

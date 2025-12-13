import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] data;
    static List<int[]> chicks;
    static List<int[]> houses;
    static boolean[] remains;

    static int result = Integer.MAX_VALUE;

    static int find(){
        int sum = 0;
        for(int i=0;i<houses.size();i++){
            int min = Integer.MAX_VALUE;
            for(int j=0;j<chicks.size();j++){
                if(remains[j]){
                    min = Math.min(min, Math.abs(houses.get(i)[0] - chicks.get(j)[0]) + Math.abs(houses.get(i)[1] - chicks.get(j)[1]));
                }
            }
            sum += min;
        }

        return sum;
    }
    static void dfs(int idx, int cnt){

        if (cnt == m) {
            result = Math.min(result, find());
            return;
        }
        if (idx == chicks.size()) return;
        if(cnt + chicks.size() - idx < m) return;
        

        remains[idx] = true;
        dfs(idx+1,cnt+1);
        remains[idx] = false;
        dfs(idx+1,cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        chicks = new ArrayList<>(m);
        houses = new ArrayList<>(n);
        data = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int val = Integer.parseInt(st.nextToken());
                if(val == 1){
                    houses.add(new int[]{i,j});
                }else if(val == 2){
                    chicks.add(new int[]{i,j});
                }
                data[i][j] = val;
            }
        }

        remains = new boolean[chicks.size()];
        dfs(0,0);


        System.out.println(result);
    }
}

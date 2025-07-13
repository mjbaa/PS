import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static boolean[] selected;
    static int min = Integer.MAX_VALUE;

    static int getTotal(){
        int sum = 0;

        for(int[] house : houses){
            int min = Integer.MAX_VALUE;
            for(int i=0;i<chickens.size();i++){
                if(selected[i]){
                    int len = Math.abs(chickens.get(i)[0] - house[0]) + Math.abs(chickens.get(i)[1] - house[1]);
                    min = Math.min(min, len);
                }

            }
            sum += min;
        }

        return sum;
    }

    static void dfs(int idx, int cnt){
        if (cnt == m) {
            min = Math.min(min, getTotal());
            return;
        }
        if (idx == chickens.size()) return;

        selected[idx] = true;
        dfs(idx+1, cnt+1);
        selected[idx] = false;

        dfs(idx+1, cnt);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int value = Integer.parseInt(st.nextToken());
                if(value == 1){
                    houses.add(new int[]{i,j});
                }else if(value == 2){
                    chickens.add(new int[]{i,j});
                }
            }
        }
        selected = new boolean[chickens.size()];

        dfs(0,0);

        System.out.println(min);

    }
}

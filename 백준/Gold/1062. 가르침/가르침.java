import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    static List<String> data;
    static boolean[] visited = new boolean[26];

    static char[] chars = new char[] {'a','n','t','i','c'};
    static Set<Integer> init = new HashSet<>();

    static int max= 0;

    static void check(){
        int cnt = 0;
        for(String s : data){
            boolean flag = true;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(!visited[c-'a']) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }

        max = Math.max(max, cnt);

    }

    static void dfs(int idx, int cnt){
        if(idx == 26){
            if(cnt == k){
                check();
            }
            return;
        }

        if(cnt > k) return;

        if(visited[idx]) {
            dfs(idx+1, cnt);
        }else{
            visited[idx] = true;
            dfs(idx+1, cnt+1);
            visited[idx] = false;
            dfs(idx+1, cnt);
        }



    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        data = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            String line = br.readLine();
            data.add(line.substring(4, line.length()-4));
        }

        for(char c : chars){
            visited[c-'a'] = true;
        }

        dfs(0,5);

        System.out.println(max);

    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int L,C;
    static List<String> data;
    static StringBuilder sb = new StringBuilder();
    static boolean[] selected;

    static boolean isVowel(String c) {
        return (c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") ||  c.equals("u") );
    }


    static void dfs(int idx, int cnt, int vcnt) {
        if (cnt + (C - idx) < L) return;

        if (cnt == L) {
            if (vcnt >= 1 && (cnt - vcnt) >= 2) {
                StringBuilder sb1 = new StringBuilder();
                for (int i = 0; i < C; i++) if (selected[i]) sb1.append(data.get(i));
                sb.append(sb1).append('\n');
            }
            return;
        }

        if (idx == C) return;

        String val = data.get(idx);

        selected[idx] = true;
        dfs(idx + 1, cnt + 1, vcnt + (isVowel(val) ? 1 : 0));
        selected[idx] = false;

        dfs(idx + 1, cnt, vcnt);
    }
    
    public static void main(String[] ags) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        data = new ArrayList<>(C);
        selected =  new boolean[C];

        st =  new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            String val = st.nextToken();
            data.add(val);
        }

        Collections.sort(data);

        dfs(0,0,0);

        System.out.println(sb);

    }

}

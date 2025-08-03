import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder s;
    static StringBuilder t;
    static int result;


    static void dfs(StringBuilder cur){
        if(cur.length() == s.length()){
            if(cur.toString().equals(s.toString())){
                result = 1;
            }
            return;
        }

        if(cur.charAt(cur.length()-1) == 'A'){
            cur.deleteCharAt(cur.length()-1);
            dfs(cur);
            cur.append("A");
        }

        if(cur.charAt(0) == 'B'){
            cur.reverse();
            cur.deleteCharAt(cur.length()-1);
            dfs(cur);
            cur.append("B");
            cur.reverse();
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        s = new StringBuilder(S);
        t = new StringBuilder(T);


        dfs(t);


        System.out.println(result);
    }


}

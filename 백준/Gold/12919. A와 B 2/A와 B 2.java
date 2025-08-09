import java.io.*;
import java.util.*;

public class Main {
    static String s,t;

    static boolean flag;

    static void dfs(){
        if(s.length() == t.length()){
            if(s.equals(t)){
                flag = true;
            }
            return;
        }

        if(t.charAt(t.length()-1) == 'A'){
            t = t.substring(0,t.length()-1);
            dfs();
            t = t+'A';
        }

        if(t.charAt(0) == 'B'){
            t = t.substring(1,t.length());
            StringBuilder sb = new StringBuilder(t);
            t = sb.reverse().toString();
            dfs();
            t = t+'B';
            sb = new StringBuilder(t);
            t = sb.reverse().toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();

        dfs();
        if(flag) System.out.println(1);
        else System.out.println(0);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        char[] bomb = t.toCharArray();
        int m = bomb.length;
        StringBuilder sb = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));

            if (sb.length() >= m && sb.charAt(sb.length() - 1) == bomb[m - 1]) {
                boolean flag = true;
                for (int j = 0; j < m; j++) {
                    if (sb.charAt(sb.length() - m + j) != bomb[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    sb.setLength(sb.length() - m);
                }
            }
        }

        if (sb.length() == 0) System.out.println("FRULA");
        else System.out.println(sb);
    }
}

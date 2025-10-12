import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] st = new int[10000];
        int st_ptr = -1;

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            String s = stz.nextToken();

            if (s.equals("push")) {
                int k = Integer.parseInt(stz.nextToken());
                st[++st_ptr] = k;
            }
            else if (s.equals("pop")) {
                if (st_ptr < 0) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(st[st_ptr--]).append('\n');
                }
            }
            else if (s.equals("size")) {
                sb.append(st_ptr + 1).append('\n');
            }
            else if (s.equals("empty")) {
                sb.append(st_ptr < 0 ? 1 : 0).append('\n');
            }
            else if (s.equals("top")) {
                if (st_ptr < 0) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(st[st_ptr]).append('\n');
                }
            }
            else {
                sb.append("err").append('\n');
            }
        }

        System.out.print(sb);
    }
}

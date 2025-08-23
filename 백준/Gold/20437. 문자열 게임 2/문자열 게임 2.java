import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            char[] s = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());

            List<Integer>[] pos = new ArrayList[26];
            for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();

            for (int i = 0; i < s.length; i++) {
                char c = s[i];
                pos[c - 'a'].add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = -1;

            for (int ch = 0; ch < 26; ch++) {
                List<Integer> list = pos[ch];
                if (list.size() < K) continue;

                for (int i = 0; i + K - 1 < list.size(); i++) {
                    int L = list.get(i);
                    int R = list.get(i + K - 1);
                    int len = R - L + 1;

                    if (len < minLen) minLen = len;
                    if (len > maxLen) maxLen = len;
                }
            }

            if (maxLen == -1) sb.append(-1).append('\n');
            else sb.append(minLen).append(' ').append(maxLen).append('\n');
        }

        System.out.print(sb);
    }
}

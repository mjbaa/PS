import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String line;

        while (!(line = br.readLine()).equals(".")) {
            int len = line.length();
            int[] pi = new int[len];

            for (int i = 1, j = 0; i < len; i++) {
                while (j > 0 && line.charAt(i) != line.charAt(j)) {
                    j = pi[j - 1];
                }
                if (line.charAt(i) == line.charAt(j)) {
                	
                    pi[i] = j+1;
                    j++;
                }
            }

            int l = pi[len - 1]; // 전체 text에서 접두사 == 접미사 인 가장 긴 접두사(접미사) 길이
            int patternLength = len - l; // 전체 text가 특정 패턴의 반복일 때 가장 긴 접두사(접미사) 제외한 서브문자열이 최소 패턴

            if (len % patternLength == 0) {
                sb.append(len / patternLength).append("\n");
            } else {
                sb.append(1).append("\n");
            }
        }

        System.out.print(sb);
    }
}

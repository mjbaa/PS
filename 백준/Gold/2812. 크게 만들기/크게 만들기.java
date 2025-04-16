import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int remove = k;

        String line = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            // 작은 숫자는 제거
            while (!stack.isEmpty() && remove > 0 && stack.peek() < cur) {
                stack.pop();
                remove--;
            }
            stack.push(cur);
        }

        while (remove-- > 0) {
            stack.pop();
        }


        for (char c : stack) {
            sb.append(c);
        }

        System.out.println(sb);
    }
}

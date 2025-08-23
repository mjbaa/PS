import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Deque<Integer> stack = new ArrayDeque<>();
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //첫번째 탑 : 항상 신호 받을 탑 없음
        StringBuilder sb = new StringBuilder("0 ");

        n = Integer.parseInt(br.readLine());

        data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        //첫번째 탑 : 항상 신호 받을 탑 없음
        stack.push(0);

        for(int i=1;i<n;i++){
            int cur = data[i];

            if(stack.isEmpty()){
                sb.append("0 ");
                continue;
            }
            //현재보다 작은 탑은 필요 없음
            while (!stack.isEmpty() && data[stack.peek()] <= cur) {
                stack.pop();
            }

            if (stack.isEmpty()) sb.append("0 ");
            else sb.append(stack.peek() + 1).append(" ");

            stack.push(i);
        }

        System.out.println(sb);
    }
}





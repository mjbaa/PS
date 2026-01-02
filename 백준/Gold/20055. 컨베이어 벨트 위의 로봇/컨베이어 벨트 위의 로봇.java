import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] data;       // 내구도
    static boolean[] slots;  // 로봇 위치
    static int ptr;          // 올리는 위치
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int size = 2 * n;
        data = new int[size];
        slots = new boolean[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            if (data[i] == 0) cnt++;
        }

        ptr = 0;
        int step = 0;

        while (true) {
            step++;

            // 벨트 회전
            ptr = (ptr - 1 + size) % size;
            int down = (ptr + n - 1) % size;
            slots[down] = false; // 내려가기

            // 로봇 이동
            for (int i = 1; i <= n; i++) {
                int cur = (down - i + size) % size;
                int next = (cur + 1) % size;

                if (slots[cur] && !slots[next] && data[next] > 0) {
                    slots[cur] = false;
                    slots[next] = true;
                    data[next]--;
                    if (data[next] == 0) cnt++;
                }
            }
            slots[down] = false;

            // 로봇 올리기
            if (data[ptr] > 0 && !slots[ptr]) {
                slots[ptr] = true;
                data[ptr]--;
                if (data[ptr] == 0) cnt++;
            }

            // 종료
            if (cnt >= k) break;
        }

        System.out.println(step);
    }
}

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        LinkedHashSet<String> set = new LinkedHashSet<>();

        for (int i = 0; i < l; i++) {
            String num = br.readLine();
            set.remove(num);  // 기존에 존재하면 삭제
            set.add(num);     // 다시 추가 (가장 뒤로 이동)
        }

        int count = 0;
        for (String num : set) {
            System.out.println(num);
            count++;
            if (count == k) break; // k개만 출력 후 종료
        }
    }
}

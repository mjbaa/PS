import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for(int i = 0; i < n; i++){ // 각 칸에 대해 data[i] == 0 만들면서 카운트
            if(data[i] == 0) continue;

            if(i < n-2){ // 3칸 가능
                if (data[i+1] > data[i+2]) { // 중간칸이 더 크면 최대한 2개 구매로 빼기
                    int min = Math.min(data[i], data[i+1] - data[i+2]);
                    sum += 5 * min;
                    data[i] -= min;
                    data[i+1] -= min;
                }

                // 3칸 단위
                int min = Math.min(data[i], Math.min(data[i+1], data[i+2]));
                sum += 7 * min;
                data[i] -= min;
                data[i+1] -= min;
                data[i+2] -= min;
            }

            if(i < n-1){ // 2칸 가능 -> 2칸단위 계산
                int min = Math.min(data[i], data[i+1]);
                sum += 5 * min;
                data[i] -= min;
                data[i+1] -= min;
            }

            //1칸단위 남음
            sum += 3 * data[i];
            data[i] = 0;


        }

        System.out.println(sum);
    }
}

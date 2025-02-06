import java.util.*;
import java.io.*;

public class Main {
    static int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 월별 일수

    // 특정 날짜를 연초부터의 총 일수로 변환하는 함수
    static int parse(int m, int d) {
        int days = 0;
        for (int i = 1; i < m; i++) {
            days += months[i];
        }
        return days + d;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] data = new int[n][2];
        StringTokenizer st;

        // 입력값을 일수로 변환하여 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int ms = Integer.parseInt(st.nextToken());
            int ds = Integer.parseInt(st.nextToken());
            int me = Integer.parseInt(st.nextToken());
            int de = Integer.parseInt(st.nextToken());

            data[i][0] = parse(ms, ds);
            data[i][1] = parse(me, de);
        }

        // 탐색할 범위 (3월 1일 ~ 11월 30일)
        int start = parse(3, 1);
        int end = parse(11, 30);

        // 시작일 기준 오름차순 정렬, 같은 경우 종료일 내림차순 정렬
        Arrays.sort(data, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int cnt = 0;     // 선택한 구간의 개수
        int prevEnd = start; // 현재까지의 마지막 종료일
        int i = 0;

        while (i < n) {
            int maxEnd = -1; // 현재 선택 가능한 구간 중 가장 큰 종료일
            int maxIdx = -1;

            // 현재 구간이 prevEnd 안에 포함되는 동안 최적의 종료일을 찾음
            while (i < n && data[i][0] <= prevEnd) {
                if (data[i][1] > maxEnd) {
                    maxEnd = data[i][1];
                    maxIdx = i;
                }
                i++;
            }

            // 더 이상 선택할 구간이 없음
            if (maxIdx == -1) {
                System.out.println(0);
                return;
            }

            prevEnd = maxEnd; // 가장 긴 종료일을 가진 구간으로 업데이트
            cnt++;

            // 목표 범위를 넘으면 종료
            if (prevEnd > end) {
                System.out.println(cnt);
                return;
            }
        }

        // 종료 조건을 만족하지 못하면 0 출력
        System.out.println(0);
    }
}

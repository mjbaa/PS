import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] data;
    static int[] dy = {-1, 0, 1};

    static boolean notRange(int y) {
        return (y < 0 || y >= 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        data = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];


        for (int j = 0; j < 3; j++) {
            maxDp[0][j] = data[0][j];
            minDp[0][j] = data[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int maxVal = Integer.MIN_VALUE;
                int minVal = Integer.MAX_VALUE;

                for (int k = 0; k < 3; k++) {
                    int ny = j + dy[k];
                    if (notRange(ny)) continue;
                    maxVal = Math.max(maxVal, maxDp[(i - 1) % 2][ny]);
                    minVal = Math.min(minVal, minDp[(i - 1) % 2][ny]);
                }

                maxDp[i % 2][j] = maxVal + data[i][j];
                minDp[i % 2][j] = minVal + data[i][j];
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int j = 0; j < 3; j++) {
        	max = Math.max(max, maxDp[(n - 1) % 2][j]);
        	min = Math.min(min, minDp[(n - 1) % 2][j]);
        }

        System.out.println(max + " " + min);
    }
}

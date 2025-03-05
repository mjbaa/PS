
import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer>[][] data;
    static int[] moveA, moveB;
    static int[][] bc;
    static int[] dx = {0, -1, 0, 1, 0}; // 정지, 상, 우, 하, 좌
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= t; tc++) {
            data = new ArrayList[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    data[i][j] = new ArrayList<>();
                }
            }

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            moveA = new int[m];
            moveB = new int[m];
            bc = new int[a][4]; // x, y, 범위, 성능

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) moveA[i] = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) moveB[i] = Integer.parseInt(st.nextToken());
            
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    bc[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int b = 0; b < a; b++) {
                int x = bc[b][1] - 1;
                int y = bc[b][0] - 1;
                int c = bc[b][2];
                int p = bc[b][3];

                for (int i = x - c; i <= x + c; i++) {
                    for (int j = y - c; j <= y + c; j++) {
                        if (i < 0 || j < 0 || i >= 10 || j >= 10) continue;
                        if (Math.abs(x - i) + Math.abs(y - j) > c) continue;
                        data[i][j].add(b);
                    }
                }
            }

            int sum = 0;
            int ax = 0, ay = 0, bx = 9, by = 9;

            for (int i = -1; i < m; i++) {
                if (i != -1) {
                    ax += dx[moveA[i]];
                    ay += dy[moveA[i]];
                    bx += dx[moveB[i]];
                    by += dy[moveB[i]];
                }
                sum += getMaxCharge(ax, ay, bx, by);
            }
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.print(sb);
    }

    private static int getMaxCharge(int ax, int ay, int bx, int by) {
        List<Integer> listA = data[ax][ay];
        List<Integer> listB = data[bx][by];
        
        int maxCharge = 0;
        
        if (listA.isEmpty() && listB.isEmpty()) return 0;
        if (listA.isEmpty()) return getMaxBC(listB);
        if (listB.isEmpty()) return getMaxBC(listA);
        
        for (int a : listA) {
            for (int b : listB) {
                if (a == b) {
                    maxCharge = Math.max(maxCharge, bc[a][3]);
                } else {
                    maxCharge = Math.max(maxCharge, bc[a][3] + bc[b][3]);
                }
            }
        }
        return maxCharge;
    }
    
    private static int getMaxBC(List<Integer> list) {
        int max = 0;
        for (int i : list) {
            max = Math.max(max, bc[i][3]);
        }
        return max;
    }
}

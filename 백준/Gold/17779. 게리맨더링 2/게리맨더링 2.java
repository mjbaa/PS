import java.util.*;
import java.io.*;


class Main {
	static int[][] data;
	static int n;
	static int min = Integer.MAX_VALUE;
	static int totalSum = 0;
	static boolean[][] border;
	
	static List<int[]> findD(int x, int y) {
		List<int[]> result = new ArrayList<>();
		
		int d1Max = Math.min(n-x, n-y);
		
		for(int d1 = 1;d1<d1Max;d1++) {
			int d2Max = Math.min(n-y, n-x-d1);
			for(int d2 = 1; d2 < d2Max;d2++) {
				result.add(new int[] {d1,d2});
			}
		}
//		int d1Max = Math.min(n - x - 1, y);
//		for (int d1 = 1; d1 <= d1Max; d1++) {
//		    int d2Max = Math.min(n - (y + d1) - 1, x + d1);
//		    for (int d2 = 1; d2 <= d2Max; d2++) {
//		        result.add(new int[]{d1, d2});
//		    }
//		}

		
		return result;//d1, d2 의 list
	}
	
	static void findMin(int x, int y, int d1, int d2) {
		border = new boolean[n][n];
		// 경계선 세팅
        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }
		
        int[] peopleSum = new int[5];

        // 1 구역 인구수
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                peopleSum[0] += data[i][j];
            }
        }

        // 2 구역 인구수
        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (border[i][j]) break;
                peopleSum[1] += data[i][j];
            }
        }

        // 3 구역 인구수
        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                peopleSum[2] += data[i][j];
            }
        }

        // 4 구역 인구수
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                peopleSum[3] += data[i][j];
            }
        }
		
        // 5 구역 인구수
        peopleSum[4] = totalSum;

        for (int i = 0; i < 4; i++) {
            peopleSum[4] -= peopleSum[i];
        }

        // 정렬
        Arrays.sort(peopleSum);

        // 최대 - 최소
        min = Math.min(min, peopleSum[4] - peopleSum[0]);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        
        
        StringTokenizer st;
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		data[i][j] = Integer.parseInt(st.nextToken());
        		totalSum+=data[i][j];
        	}
        }
        
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (x + d1 + d2 >= n) continue;
                        if (y - d1 < 0 || y + d2 >= n) continue;

                        findMin(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);
        
    }
    
    
}
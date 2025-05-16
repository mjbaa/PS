//import java.io.*;
//import java.util.*;
//
//public class Main {
//	static int n, k;
//	static List<Integer>[] books;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		n = Integer.parseInt(st.nextToken());
//		k = Integer.parseInt(st.nextToken());
//
//		books = new List[11];
//		for (int i = 1; i <= 10; i++) books[i] = new ArrayList<>();
//
//		for (int i = 0; i < n; i++) {
//			st = new StringTokenizer(br.readLine());
//			int cost = Integer.parseInt(st.nextToken());
//			int genre = Integer.parseInt(st.nextToken());
//			books[genre].add(cost);
//		}
//
//		for (int i = 1; i <= 10; i++) {
//			Collections.sort(books[i], (a, b) -> b - a);
//			
//			for (int j = 1; j < books[i].size(); j++) {
//				books[i].set(j, books[i].get(j) + books[i].get(j - 1));
//			}
//			
//			for (int j = 0; j < books[i].size(); j++) {
//				int bonus = (j + 1) * (j + 2) / 2;
//				books[i].set(j, books[i].get(j) + bonus);
//			}
//		}
//
//		int[][] dp = new int[11][k + 1];
//		for (int i = 1; i <= 10; i++) {
//			for (int j = 0; j <= k; j++) {
//				dp[i][j] = dp[i - 1][j];
//				for (int l = 1; l <= books[i].size(); l++) {
//					if (j - l < 0) break;
//					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - l] + books[i].get(l - 1));
//				}
//			}
//		}
//
//		System.out.println(dp[10][k]);
//	}
//}
//
import java.io.*;
import java.util.*;

public class Main {
    static int[] dp = new int[2001]; // k ≤ 2000
    static List<Integer>[] book = new ArrayList[11];
    static List<Integer>[] sum = new ArrayList[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 10; i++) {
            book[i] = new ArrayList<>();
            sum[i] = new ArrayList<>();
        }


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int genre = Integer.parseInt(st.nextToken());
            book[genre].add(cost);
        }

        for (int i = 1; i <= 10; i++) {
            List<Integer> genbook = book[i];
            genbook.sort(Collections.reverseOrder());

            int acc = 0;
            for (int j = 0; j < genbook.size(); j++) {
                acc += genbook.get(j);
                sum[i].add(acc);
            }
        }


        for (int i = 1; i <= 10; i++) {
            List<Integer> books = sum[i];

            for (int baseCnt = k; baseCnt >= 0; baseCnt--) {
                for (int j = 1; j <= books.size(); j++) {
                    if (baseCnt + j > k) break;
                    int bonus = (j - 1) * j;
                    dp[baseCnt + j] = Math.max(dp[baseCnt + j], dp[baseCnt] + books.get(j - 1) + bonus);
                }
            }
        }

        System.out.println(dp[k]);
    }
}



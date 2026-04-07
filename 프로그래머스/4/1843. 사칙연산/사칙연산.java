class Solution {
    int n;
    String[] data;
    int[][] maxDp;
    int[][] minDp;

    public int solution(String[] arr) {
        data = arr;
        n = arr.length / 2 + 1;

        maxDp = new int[n][n];
        minDp = new int[n][n];

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(data[2 * i]);
            maxDp[i][i] = num;
            minDp[i][i] = num;
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;

                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;

                for (int mid = i; mid < j; mid++) {
                    String op = data[2 * mid + 1];

                    if (op.equals("+")) {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][mid] + maxDp[mid + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][mid] + minDp[mid + 1][j]);
                    } else {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][mid] - minDp[mid + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][mid] - maxDp[mid + 1][j]);
                    }
                }
            }
        }

        return maxDp[0][n - 1];
    }
}
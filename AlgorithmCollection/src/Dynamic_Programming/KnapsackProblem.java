package Dynamic_Programming;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weights = {0, 2, 3, 4, 7, 2};
        int[] values = {0, 1, 3, 5, 9, 7};
        int capacity = 10;
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println("最大价值为" + dp[n][capacity]);
    }
}



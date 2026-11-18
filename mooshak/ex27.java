import java.io.*;
import java.util.Arrays;

class ex27 {

    private static long aux(int i, int j, int[] arr, int[] prefix, long[][] dp) {
        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        long minSmoke = Long.MAX_VALUE;
        for (int k = i; k < j; k++) {
            long leftColor = (prefix[k + 1] - prefix[i]) % 100;
            long rightColor = (prefix[j + 1] - prefix[k + 1]) % 100;
            // solve left and solve right
            long total = aux(i, k, arr, prefix, dp)
                    + aux(k + 1, j, arr, prefix, dp)
                    + leftColor * rightColor;

            if (total < minSmoke) {
                minSmoke = total;
            }
        }
        return dp[i][j] = minSmoke;
    }

    /**
     * The logic goes as creating a prefix array of the colors, so it can be
     * used to find easier the color of the left and right mix, and work
     * around the idea of creating, recursively, separations of left and right
     * in the array, using a for iterate through the separations going from
     * 0~j.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        StreamTokenizer in =
                new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        in.nextToken(); int cases = (int) in.nval;
        for (int z = 0; z < cases; z++) {
            in.nextToken(); int n = (int) in.nval;
            int[] arr = new int[n];
            int[] prefix = new int[n + 1];
            for (int i = 0; i < n; i++) {
                in.nextToken(); arr[i] = (int) in.nval;
                prefix[i + 1] = prefix[i] + arr[i];
            }

            long[][] dp = new long[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            out.println(aux(0, n - 1, arr, prefix, dp));
        }
        out.close();
    }
}
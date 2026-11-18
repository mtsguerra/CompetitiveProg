import java.io.*;
import java.util.Arrays;

class ex26 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in =
                new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        in.nextToken(); int n = (int) in.nval;
        long[] arr1 = new long[n];
        for (int i= 0;i<n;i++){
            in.nextToken(); arr1[i] = (long) in.nval;
        }

        in.nextToken(); int m = (int) in.nval;
        int[] arr2 = new int[m];
        for (int i=0;i<m;i++){
            in.nextToken(); arr2[i] = (int) in.nval;
        }
        long[][] dp = new long[n+1][m+1];
        for (int i=0;i<=n;i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                long currentProduct = arr1[i-1] * arr2[j-1];
                long takeBoth = Math.max(currentProduct, dp[i-1][j-1] + currentProduct);
                long skipOne = Math.max(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.max(takeBoth, skipOne);
            }
        }
        out.println(dp[n][m]);
        out.close();
    }
}
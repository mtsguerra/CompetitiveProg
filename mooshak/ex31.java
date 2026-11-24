import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ex31 {

    private static int rec(int budget, List<Integer> bundlePrices,
                           List<Integer> bundleSizes, int index, int[][] dp){
        if (index == 0 || budget == 0) return 0;
        if (dp[index][budget] != -1) return dp[index][budget];
        int picked = 0;
        if (budget >= bundlePrices.get(index-1)){
            picked = rec(budget - bundlePrices.get(index-1), bundlePrices, bundleSizes, index-1, dp) + bundleSizes.get(index-1);
        }
        int notPicked = rec(budget, bundlePrices, bundleSizes, index-1, dp);
        return dp[index][budget] = Math.max(picked, notPicked);
    }

    public static void main(String[] args) throws IOException{
        StreamTokenizer in =
                new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int n = (int)in.nval;
        in.nextToken(); int budget = (int)in.nval;
        int[] prices = new int[n];
        int[] sizes = new int[n];
        //List<Integer> bundlePrices = new ArrayList<>();
        //List<Integer> bundleSizes = new ArrayList<>();
        int[] dp = new int[budget + 1];
        for (int i=0; i<n; i++){
            in.nextToken(); prices[i] = (int)in.nval;
        }
        for (int i=0; i<n; i++){
            in.nextToken(); sizes[i] = (int)in.nval;
        }
        for (int i=0; i<n; i++){
            in.nextToken(); int quant = (int)in.nval;
            for (int b=1; quant > 0; b <<=1){
                int curr = (int) Math.min(b, quant);
                int bundlePrice = curr * prices[i];
                int bundleSize = curr * sizes[i];
                quant = (int) (quant - curr);
                for (int w = budget; w >= bundlePrice; w--) {
                    dp[w] = Math.max(dp[w], dp[w - bundlePrice] + bundleSize);
                }
            }
        }

        //int[][] dp = new int[bundlePrices.size()+1][budget+1];
        //for (int[] row : dp) Arrays.fill(row, -1);
        out.println(dp[budget]);
        out.close();
    }
}
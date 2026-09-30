import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class ex14 {

    private static long caculateCost (int index, int height, int nBuildings,
                                      long[] prefixHeightCost, long[] prefixCosts){
        if (index == -1) {
            return prefixHeightCost[nBuildings - 1] -
                    ((long) height * prefixCosts[nBuildings - 1]);
        }
        long leftSum =
                (height * prefixCosts[index]) - prefixHeightCost[index];
        long rightSum =
                prefixHeightCost[nBuildings - 1] - prefixHeightCost[index] -
                        (height * (prefixCosts[nBuildings - 1] - prefixCosts[index]));
        return leftSum + rightSum;
    }

    private static int indexToCalc (int height, int[][] heightsAndPrices,
                                    int nBuildings){
        int right = nBuildings - 1;
        int left = 0;
        int ans = -1;
        while (right >= left){
            int mid = left + (right - left) / 2;
            if (heightsAndPrices[mid][0] <= height){
                ans = mid;
                left = mid + 1;
            } else {
                right = mid-1;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int nTestCases = Integer.parseInt(in.readLine());
        for (int l = 0; l < nTestCases; l++){
            int nBuildings = Integer.parseInt(in.readLine());
            
            int[][] heightsAndPrices = new int[nBuildings][2];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < nBuildings; i++){
                heightsAndPrices[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < nBuildings; i++){
                heightsAndPrices[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(heightsAndPrices, (a,b) -> Integer.compare(a[0], b[0]));
            if (heightsAndPrices[0][0] == heightsAndPrices[nBuildings - 1][0]){
                out.println(0);
                continue;
            }
/*
            long leftSum = 0;
            long leftPricesSum = 0;
            long rightSum = 0;
            long rightPricesSum = 0;

            // precompute all the right sums
            int startingBuilding = heightsAndPrices[0][0]-1;

            for (int[] building : heightsAndPrices){
                int crrH = building[0];
                long crrP = building[1];
                rightSum += crrP * (crrH - startingBuilding);
                rightPricesSum += crrP;
            }


            long[] prices =
                    new long[heightsAndPrices[nBuildings - 1][0] + 1 ];
            int crrIndx = 0;
            for (int i = heightsAndPrices[0][0]; i <= heightsAndPrices[nBuildings - 1][0]; i++){
                rightSum -= rightPricesSum;
                leftSum += leftPricesSum;
                while (crrIndx < nBuildings && i == heightsAndPrices[crrIndx][0]){
                    rightPricesSum -= heightsAndPrices[crrIndx][1];
                    leftPricesSum += heightsAndPrices[crrIndx][1];
                    crrIndx++;
                }
                prices[i] = leftSum + rightSum;
            }
             */

            long[] prefixCosts = new long[nBuildings];
            long[] prefixHeightCost = new long[nBuildings];
            prefixCosts[0] = heightsAndPrices[0][1];
            prefixHeightCost[0] = (long) heightsAndPrices[0][0] * heightsAndPrices[0][1];
            for (int i = 1; i < nBuildings; i++){
                prefixCosts[i] =
                        prefixCosts[i-1] + heightsAndPrices[i][1];
                prefixHeightCost[i] =
                        prefixHeightCost[i-1] + (long) heightsAndPrices[i][1] * heightsAndPrices[i][0];
            }

            int lowHeight = heightsAndPrices[0][0];
            int highHeight = heightsAndPrices[nBuildings - 1][0];
            long bestPrice = Long   .MAX_VALUE;
            while (highHeight >= lowHeight){
                int mid1 = lowHeight + (highHeight - lowHeight) / 3;
                int mid2 = highHeight - (highHeight - lowHeight) / 3;

                int mid1Indx = indexToCalc(mid1, heightsAndPrices, nBuildings);
                int mid2Indx = indexToCalc(mid2, heightsAndPrices, nBuildings);
                long mid1Price = caculateCost(mid1Indx, mid1, nBuildings,
                        prefixHeightCost, prefixCosts);
                long mid2Price = caculateCost(mid2Indx, mid2, nBuildings,
                        prefixHeightCost, prefixCosts);

                if (mid2Price > mid1Price){
                    highHeight = mid2-1;
                    bestPrice = mid1Price;
                }
                else {
                    lowHeight = mid1+1;
                    bestPrice = mid2Price;
                }
            }
            out.println(bestPrice);
        }
        out.close();
    }
}
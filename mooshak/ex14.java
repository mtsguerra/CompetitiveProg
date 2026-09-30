import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class ex14 {

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


            int lowHeight = heightsAndPrices[0][0];
            int highHeight = heightsAndPrices[nBuildings - 1][0];
            long bestPrice = Long   .MAX_VALUE;
            while (highHeight >= lowHeight){
                int mid1 = lowHeight + (highHeight - lowHeight) / 3;
                int mid2 = highHeight - (highHeight - lowHeight) / 3;

                long mid1Price = prices[mid1];
                long mid2Price = prices[mid2];

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
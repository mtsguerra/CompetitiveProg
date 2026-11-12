import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class ex21 {

    private static int[] maxContigSubarray(int[] arr){
        int maxSum[] = new int[3];
        maxSum[0] = arr[0];
        int currentSum = 0;
        int start =0;
        for (int i=0; i<arr.length;i++){
            if (currentSum<=0){
                currentSum = arr[i];
                start = i;
            }
            else {
                currentSum += arr[i];
            }
            if (maxSum[0] < currentSum){
                maxSum[0] = currentSum;
                maxSum[1] = start;
                maxSum[2] = i;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int nSide = Integer.parseInt(br.readLine());
        int[][] mtrx = new int[nSide][nSide];

        for (int i = 0; i < nSide; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nSide; j++){
                mtrx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxRecSum = Integer.MIN_VALUE;
        int maxRecLeft = 0;
        int maxRecRight = 0;
        int maxRecTop = 0;
        int maxRecBottom = 0;

        int[] rowSum = new int[nSide];
        int rowIndx = 0;
        for (int left =0; left<nSide;left++){
            Arrays.fill(rowSum, 0);
            for (int right=left; right<nSide;right++){
                rowIndx = 0;
                for (int col=0; col<nSide;col++){
                    rowSum[rowIndx] += mtrx[col][right];
                    rowIndx++;
                }
                int[] currentContigMax = maxContigSubarray(rowSum);
                if (maxRecSum < currentContigMax[0]){
                    maxRecSum = currentContigMax[0];
                    maxRecLeft = left;
                    maxRecRight = right;
                    maxRecTop = currentContigMax[1];
                    maxRecBottom = currentContigMax[2];
                }
            }
        }
        out.println(maxRecSum);
        out.close();
    }
}
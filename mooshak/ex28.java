import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class ex28 {

    private static int[] getMaxSeqAsc (int[] nums, boolean reverse){
        int m = nums.length;
        int[] arr = new int[m];
        List<Integer> tail = new ArrayList<>();
        for (int j = 0; j<m; j++) {
            int index = reverse ? (m-1-j) : j;
            int curr = nums[index];

            int left = 0;
            int right = tail.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int toCompare = tail.get(mid);
                if (toCompare < curr) {
                    left = mid + 1;
                } else if (toCompare > curr) {
                    right = mid - 1;
                } else {
                    left = mid;
                    break;
                }
            }
            if (left == tail.size()) {
                tail.add(curr);
            }
            else {
                tail.set(left, curr);
            }
            arr[index] = left + 1;
        }
        return arr;
    }

    /**
     * Iterating through the array having a tail that follows to store the
     * current sequence of ascending numbers, in such a way that every time
     * it stores a new number (searching the index to be placed with BS);
     *  1. Occupying an already existing position in the tail, it will take
     *  the current index and store the size of the tail, from where it was
     *  putten.
     *  2. Occupying the end of the tail, it will increase the size of the
     *  tail and sub consequently increase the size of the max sequence,
     *  storing the size of the tail now.
     * It will be used from the left to right and right to left, in such a
     * way that every time we look at one number, we will be looking for the
     * left and right, having this number as the peak, so to the wavio become
     * valid will have the minimum size of the two sequences formed from left
     * to right and right to left.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        StreamTokenizer in =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int n = (int)in.nval;
        for (int i = 0; i < n; i++){
            in.nextToken(); int m = (int)in.nval;
            int[] nums = new int[m];
            for (int j = 0; j < m; j++) {
                in.nextToken(); nums[j] = (int)in.nval;
            }
            int[] leftToRight = getMaxSeqAsc(nums, false);
            int[] rightToLeft = getMaxSeqAsc(nums, true);
            int maxWavio = 1;
            for (int k = 0; k<m;k++){
                int currentWavio = 2 * Math.min(leftToRight[k],
                        rightToLeft[k])-1;
                maxWavio = Math.max(maxWavio, currentWavio);
            }
            out.println(maxWavio);
        }
        out.close();
    }
}
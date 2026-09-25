import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

class exE {

    static void removeFromTree (TreeMap<Integer, Integer> suppliesMap, int sp){
        if (suppliesMap.get(sp) == 1) suppliesMap.remove(sp);
        else suppliesMap.put(sp, suppliesMap.get(sp) - 1);
    }

    /**
     * E. Packing for the Expedition
     *
     * "A team of explorers is preparing for a long expedition beyond the
     * colony. Their leader, Captain Lyra, must distribute essential supplies
     * among them. The supplies come in different sizes (representing their
     * weight or bulk), and she wants to ensure the distribution is fair. To
     * prevent overburdening any individual, she must ensure that the total
     * size of supplies carried by any single explorer is as small as
     * possible. Captain Lyra also imposes a strict rule: no explorer should
     * carry more than two items (two distinct pieces of supply), as it would
     * be too difficult to manage otherwise. Given the sizes of the supplies,
     * determine the least maximum total size (L) that any explorer will have
     * to carry."
     *
     * Approach: By using a greedy line of thought, as in trying to minimize
     * the maximum total size that any explorer will have to carry by testing
     * different combinations with a bs and a two-pointer greedy checker,
     * starting with the largest carry possible and going down until it is
     * not possible to combine the supplies in a way that satisfies a lower L.
     *
     * Time complexity: O(n log n)
     * Space complexity: O(n)
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int supplies = Integer.parseInt(st.nextToken());
        int explorers = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());

        int[] suppliesArray = new int[supplies];
        int maxSupply = Integer.MIN_VALUE;

        for (int i = 0; i < supplies; i++) {
            int supply = Integer.parseInt(st.nextToken());
            suppliesArray[i] = supply;
            maxSupply = Math.max(maxSupply, supply);
        }
        Arrays.sort(suppliesArray);

        if (supplies <= explorers) {
            out.println(maxSupply);
            out.close();
            return;
        }

        long low = maxSupply, high = 2*maxSupply; long result = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            boolean valid = isValid(suppliesArray, mid, explorers);
            if (valid) {
                result = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        out.println(result);
        out.close();
    }

    private static boolean isValid(int[] suppliesArray, long mid, int explorers) {
        boolean valid = false;
        int left = 0, right = suppliesArray.length-1, groups = 0;
        while (left <= right) {
            if (suppliesArray[right] > mid){
                break;
            }
            if (left == right){
                groups++;
                left++;
            }
            else if (suppliesArray[left] + suppliesArray[right] <= mid){
                groups++;
                left++;
                right--;
            }
            else {
                groups++;
                right--;
            }
        }
        valid = groups <= explorers;
        return valid;
    }
}
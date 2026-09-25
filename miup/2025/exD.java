import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class exD {
    /**
     * D. The Jumps of the Zylos
     *
     * "To establish a viable colony on Nova Terra, biologists are testing
     * the survivability and movement capabilities of experimental organisms
     * across the alien terrain. One such creature, a peculiar insect known
     * as the Zylos, must traverse a vast meadow of towering alien flora. The
     * colonists need to accurately map the Zylos's possible paths, which are
     * constrained by its unique jumping ability, shaped by the planet's
     * high gravity and the creature's biometrics. The alien flowers in the
     * meadow are arranged in a linear sequence, and their heights are
     * represented by a sequence of positive integers a of length N. The
     * height of the i-th flower is ai. The Zylos is only capable of jumping
     * from left to right (from a flower i to a flower j where i < j). A jump
     * is possible only if the height difference between the two flowers does
     * not exceed a given limit. Specifically, from flower i, the Zylos can
     * jump to flower j if and only if i < j and the absolute difference in
     * heights satisfies |ai - aj| ≤ K. The constant K is a positive integer
     * that represents the maximum allowed height difference."
     *
     * Approach: for it only moves from left to right, it can be solved while
     * reading the input. For it, I start with an initial range from the
     * starting flower going for +maxJump and -maxJump, and afterward all
     * the new flowers that are within the range add their new range to it,
     * updating the range.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param args info
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int nFlowers = Integer.parseInt(st.nextToken());
        int maxJump = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        out.print(1);
        int left = Integer.parseInt(st.nextToken()) - maxJump;
        int right = left + 2* maxJump;
        for (int i = 1; i < nFlowers; i++){
            out.print(" ");
            int num = Integer.parseInt(st.nextToken());
            if (num >= left && num <= right){
                out.print(1);
                if (num+maxJump > right) right = num + maxJump;
                else if (num-maxJump > 0 && num-maxJump < left) left =
                        num - maxJump;
            }
            else out.print(0);
        }
        out.println();
        out.close();
    }
}
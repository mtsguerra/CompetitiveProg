import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

class ex2 {
    /**
     * [PC002] Encryption
     *
     * "Given two strings A and B, you have to check if A is a subsequence of B,
     * that is, if you can remove characters from B such that the concatenation
     * of the remaining characters is A."
     *
     * Approach: Using a two pointer method in such a way that it iterates
     * through the B word looking for the current match to form A word
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param args the number of duos to verify and the words
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        for (int i=0; i<n; i++) {
            StringTokenizer words = new StringTokenizer(br.readLine());
            String s = words.nextToken();
            String t = words.nextToken();
            int k = 0;
            boolean found = false;
            for (int j=0; j<t.length(); j++) {
                if (t.charAt(j)==s.charAt(k)) {
                    if (k++ == s.length()-1) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}
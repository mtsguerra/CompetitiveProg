import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;


class ex3 {
    /**
     * [PC003] Trailing Zeros
     *
     * "Your task is to calculate the number of trailing zeros in the
     * factorial n!. For example, 20! = 2.432902e+18 and it has 4 trailing
     * zeros."
     *
     * Approach: To find out the number of trailing zeros in a factorial, we
     * can count the number of times 5 is a factor in the numbers from 1 to n.
     * This is because each pair of 2 and 5 contributes to a trailing zero,
     * and there 5 factors are rarer than 2 ones.
     *
     * Time complexity = O(log n)
     * Space complexity = O(1)
     * @param args gives me the number to find out
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long aux = 5;
        long result = 0;
        while (n >= aux){
            result += n/aux;
            aux *= 5;
        }
        System.out.println(result);
    }
}
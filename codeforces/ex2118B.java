import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ex2118B {
    /**
     * 2118B codeforces
     *
     * Approach: using a split-reversal logic I can reverse a crescent prefix /
     * decrescent sufix in such a way to create at the end n unique
     * permutations forming then a perfect permutated matrix
     *
     * @param args gives me the matrix sizes
     * @throws IOException trust codeforces
     */
    public static void main(String[] args) throws IOException {

        PrintWriter out = new PrintWriter(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nTests = Integer.parseInt(br.readLine());

        for (int i = 0; i < nTests; i++) {
            int n = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();
            int totalOps=1;
            sb.append(1).append(" ").append(2).append(" ").append(n).append("\n");
            for (int j = 2; j < n; j++) {
                sb.append(j).append(" ").append(1).append(" ").append(j).append("\n");
                totalOps++;
                if (n-j>1){
                    sb.append(j).append(" ").append(j+1).append(" ").append(n).append("\n");
                    totalOps++;
                }
            }
            sb.append(n).append(" ").append(1).append(" ").append(n);
            totalOps++;
            /*
            sb.append(1).append(" ").append(1).append(" ").append(n-1).append("\n");
            for (int j = 2; j < n; j++) {
                if(n-j > 1){
                    sb.append(j).append(" ").append(1).append(" ").append(n-j).append("\n");
                    totalOps++;
                }
                sb.append(j).append(" ").append(n-j+1).append(" ").append(n).append("\n");
                totalOps++;
            }
            sb.append(n).append(" ").append(1).append(" ").append(n);
             */
            out.println(totalOps);
            out.println(sb);
        }
        out.flush();
    }
}
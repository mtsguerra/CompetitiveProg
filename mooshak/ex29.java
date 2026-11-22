import java.io.*;
import java.util.Arrays;

class ex29 {
    private static int nMoves (int i, int j, int[][] dp, String s){
        if (i>=j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        if (s.charAt(i) == s.charAt(j)) return dp[i][j] =
                nMoves(i+1, j-1, dp, s);
        int replace = nMoves(i+1, j-1, dp, s);
        int insert = nMoves(i+1, j, dp, s);
        int delete = nMoves(i, j-1, dp, s);
        return dp[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
    }

    /**
     * For every contiguous substring of s, find the minimum number of moves
     * to make it a palindrome, using a dp method by comparing the characters
     * on the edges, and if not equal, there will be three ways of solving
     * this problem; replace, insert, delete, and from it choose the less
     * cost option using recursion
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        StreamTokenizer in =
                new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int n = (int)in.nval;
        for (int i = 0; i < n; i++ ){
            in.nextToken(); String s = in.sval;
            int[][] dp = new int[s.length()+1][s.length()+1];
            for (int[] row : dp) Arrays.fill(row, -1);
            out.println(nMoves(0, s.length()-1, dp, s));
        }
        out.close();
    }
}
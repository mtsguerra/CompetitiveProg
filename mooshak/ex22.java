import java.io.*;
import java.util.*;

class ex22 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int nEast = (int)in.nval;
        in.nextToken(); int nWest = (int)in.nval;
        in.nextToken(); int nLines = (int)in.nval;
        long[] edges = new long[nLines];
        for (int i = 0; i < nLines; i++) {
            in.nextToken(); long u = (long)in.nval;
            in.nextToken(); long v = (long)in.nval;
            edges[i] = u * (nWest+1) + v;
        }
        Arrays.sort(edges);
        int[] bit = new int[nWest+1];
        long count = 0;
        //fenwick tree
        for (int i=0;i<nLines;i++){
            int v = (int) edges[i] % (nWest+1);
            int lower = 0;
            for (int x = v; x > 0; x -= x & -x) lower += bit[x];
            count += i - lower;
            for (int x = v; x <= nWest; x += x & -x) bit[x]++;
        }
        out.println(count);
        out.close();
    }
}
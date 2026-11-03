import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class SegmentTreeMax {
    private final int[][] tree;
    private final int n;

    public SegmentTreeMax (int[][] soldiers){
        n = soldiers.length;
        tree = new int[n*2][2];
        System.arraycopy(soldiers, 0, tree, n, n);
        for (int i=n-1; i> 0; i--){
            if (tree[i*2][0] > tree[i*2+1][0]){
                tree[i][0] = tree[i*2][0];
                tree[i][1] = tree[i*2][1];
            }
            else if (tree[i*2][0] < tree[i*2+1][0]){
                tree[i][0] = tree[i*2+1][0];
                tree[i][1] = tree[i*2+1][1];

            }
            else{
                tree[i][0] = tree[i*2][0];
                tree[i][1] = tree[i*2][1] + tree[i*2+1][1];
            }
        }
    }

    public int[][] getTree() {
         return tree;
    }
}

public class ex15{

    private static int[][] findsMax (int from, int to, int n,
                                     int[][] tree){
        from += n;
        to += n +1 ;
        int max = Integer.MIN_VALUE;
        int countMax = 0;
        while (to > from) {
            if (from % 2 == 1){
                if (tree[from][0] == max){
                    countMax+= tree[from][1];
                }
                else if (tree[from][0] > max){
                    max = tree[from][0];
                    countMax = tree[from][1];
                }
                from++;
            }
            if (to % 2 == 1){
                to--;
                if (tree[to][0] == max){
                    countMax+= tree[to][1];
                }
                else if (tree[to][0] > max){
                    max = tree[to][0];
                    countMax = tree[to][1];
                }
            }
            to/=2;
            from/=2;
        }
        return new int[][]{{max, countMax}};
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nSoldiers = Integer.parseInt(st.nextToken());
        int nQuerries = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[][] soldiers = new int[nSoldiers][2];
        for (int i=0; i<nSoldiers; i++){
            soldiers[i][0] = Integer.parseInt(st.nextToken());
            soldiers[i][1] = 1;
        }

        SegmentTreeMax stMax = new SegmentTreeMax(soldiers);
        int[][] tree = stMax.getTree();

        for (int i = 0; i < nQuerries; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            if (from == to) {
                out.println(tree[from+nSoldiers][0] + " " + tree[from+nSoldiers][1]);
                continue;
            }
            int[][] max = findsMax(from, to, nSoldiers, tree);
            out.println(max[0][0] + " " + max[0][1]);
        }
        out.close();
    }
}
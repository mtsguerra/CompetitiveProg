import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ex25 {

    private static void update(long[][] bit, int maxX, int maxY, int x, int y,
                               int v){
        for (int i = x+1; i <=maxX+1; i += i &-i){
            for (int j = y+1; j<=maxY+1; j+=j&-j){
                bit[i][j]+=v;
            }
        }
    }

    private static int query(long[][] bit, int maxX, int maxY, int x, int y){
        int sum = 0;
        for (int i = x+1; i > 0; i -= i &-i){
            for (int j = y+1; j > 0; j -= j &-j){
                 sum += bit[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int nTrees = (int)in.nval;
        // read all before
        int[] tx = new int[nTrees];
        int[] ty = new int[nTrees];
        int[] tv = new int[nTrees];
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int z = 0; z < nTrees; z++){
            in.nextToken(); int x = (int)in.nval;
            in.nextToken(); int y = (int)in.nval;
            in.nextToken(); int v = (int)in.nval;
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            tx[z] = x;
            ty[z] = y;
            tv[z] = v;
        }

        long[][] bit = new long[maxX+1][maxY+1];

        in.nextToken(); int proposals = (int)in.nval;
        for (int z = 0; z < proposals; z++){
            in.nextToken(); int x1 = (int)in.nval;
            in.nextToken(); int y1 = (int)in.nval;
            in.nextToken(); int x2 = (int)in.nval;
            in.nextToken(); int y2 = (int)in.nval;
            if (x1 > maxX){
                out.println(0);
            }
            else if (x2 > maxX){
                x2 = maxX;
            }
            if (y1 > maxY){
                out.println(0);
            }
            else if (y2 > maxY){
                y2 = maxY;
            }
        }

        out.close();
    }
}
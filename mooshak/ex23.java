import java.io.*;

class ex23 {

    static void upd(long[] b, int i, long v, int n) { for (; i <= n; i += i & -i) b[i] += v; }
    static long qry(long[] b, int i) { long s = 0; for (; i > 0; i -= i & -i) s += b[i]; return s; }
    static long pre(long[] bit1, long[] bit2, int x) { return qry(bit1, x) * x - qry(bit2,
            x); }


    public static void main(String[] args) throws IOException{
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int n = (int)in.nval;
        in.nextToken(); int c = (int)in.nval;

        long[] bit1 = new long[n+2];
        long[] bit2 = new long[n+2];

        for (int i =0; i<c; i++){
            in.nextToken(); int flag = (int)in.nval;
            in.nextToken(); int indexStart = (int)in.nval;
            in.nextToken(); int indexEnd = (int)in.nval;
            if (flag == 0){
                in.nextToken(); long toAdd = (long)in.nval;
                upd(bit1, indexStart,   toAdd, n);
                upd(bit1, indexEnd + 1, -toAdd, n);
                upd(bit2, indexStart,   toAdd * (indexStart - 1), n);
                upd(bit2, indexEnd + 1, -toAdd * indexEnd, n);
            }
            else {
                out.println(pre(bit1, bit2, indexEnd) - pre(bit1, bit2, indexStart - 1));
            }
        }
        out.close();
    }
}
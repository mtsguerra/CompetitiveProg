import java.io.*;

class ex24 {

    private static void update(int x, int y, int val, int n, int bit[][]) {
        for (int i = x + 1; i <= n; i += i & -i){
            for (int j = y+1; j <= n; j += j & -j){
                bit[i][j] += val;
            }
        }
    }

    private static int query(int x, int y, int n, int bit[][]) {
        int sum = 0;
        for (int i = x+1; i>0; i-= i & -i) {
            for (int j = y+1; j>0; j-= j & -j){
                sum += bit[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        StreamTokenizer in =
                new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int n = (int) in.nval;
        int[][] bit = new int[n+1][n+1];
        in.nextToken(); String flag = in.sval;
        while (!flag.equals("END")){
            switch (flag){
                case "SET":
                    in.nextToken(); int x = (int) in.nval;
                    in.nextToken(); int y = (int) in.nval;
                    in.nextToken(); int val = (int) in.nval;
                    int curr = query(x, y, n, bit) - query(x - 1, y, n, bit)
                            - query(x, y - 1, n, bit) + query(x - 1, y - 1, n, bit);
                    update(x, y, val - curr, n, bit);
                    break;
                case "SUM":
                    in.nextToken(); int x1 = (int) in.nval;
                    in.nextToken(); int y1 = (int) in.nval;
                    in.nextToken(); int x2 = (int) in.nval;
                    in.nextToken(); int y2 = (int) in.nval;
                    //sumOB - OA - OD + OC
                    int sum = query(x2, y2, n, bit) - query(x1-1, y2, n, bit)
                            - query(x2, y1-1, n, bit) + query(x1-1, y1-1, n, bit);
                    out.println(sum);
                    break;
                default:
                    break;
            }
            in.nextToken(); flag = in.sval;
        }
        out.close();
    }
}
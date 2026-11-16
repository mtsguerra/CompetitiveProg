import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

class ex0276C {

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); int nElem = (int) in.nval;
        in.nextToken(); int nQueries = (int) in.nval;

        PriorityQueue<Integer> numbersHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < nElem; i++) {
            in.nextToken(); numbersHeap.offer((int) in.nval);
        }

        int[] appIndex = new int[nElem];
        int[] auxSums = new int[nElem+1];
        PriorityQueue<Integer> appearancesHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 0; i < nQueries; i++) {
            in.nextToken(); int start = (int) in.nval-1;
            in.nextToken(); int end = (int) in.nval-1;
            auxSums[start]++;
            auxSums[end+1]--;
        }
        int auxAdd = 0;
        for (int i = 0; i < nElem; i++) {
            auxAdd += auxSums[i];
            appIndex[i] = auxAdd;
        }
        for (int n : appIndex) appearancesHeap.offer(n);

        long sum = 0;
        while (!appearancesHeap.isEmpty()) {
            int a = appearancesHeap.poll();
            int n = numbersHeap.poll();
            sum += (long) a*n;
        }
        out.println(sum);
        out.close();
    }
}
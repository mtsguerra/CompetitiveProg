import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class maxSegTree {

    private final int n;
    private final int[] tree;

    public maxSegTree(List<Integer> numbers){
        this.n = numbers.size();
        this.tree = new int[2*n];
    }

    public int maxFromInterval (int from, int to){
        from += n;
        to += n;
        int max = Integer.MIN_VALUE;
        if (from % 2 == 1){
            max = Math.max(max, tree[from]);
            from++;
        }
        if (to % 2 == 0){
            to--;
            max = Math.max(max, tree[to]);
        }
        return max;
    }

}

class ex16 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nNumbers = Integer.parseInt(st.nextToken());
        int nQueries = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> numbersSequenceCount = new ArrayList<>();
        int currentCount = 1;
        int lastOne = Integer.parseInt(st.nextToken());
        for (int i=1;i<nNumbers;i++){
            int currentOne = Integer.parseInt(st.nextToken());
            if (currentOne == lastOne){
                currentCount++;
            } else {
                numbersSequenceCount.add(currentCount);
                currentCount = 1;
            }
            lastOne = currentOne;
        }
        numbersSequenceCount.add(currentCount);
        maxSegTree max = new maxSegTree(numbersSequenceCount);

        for (int i=0;i<nQueries;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

        }
    }

}
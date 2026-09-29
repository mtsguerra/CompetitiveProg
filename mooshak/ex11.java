import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class ex11 {
    /**
     * [PC011] Special Sort
     *
     * "You are given an array of n distinct integers. Your task is to sort
     * the array into descending order according to the number of 1's in
     * their binary representation. If two numbers have the same number of
     * 1's, you have to sort them in ascending order of their value."
     *
     * Approach: By storing all the numbers in a list, I can create a custom
     * comparator that sorts the number in descending order by the number of
     * 1's in their binary number, and if tied on this, sorting them in
     * ascending order.
     *
     * Time complexity: O(nlogn)
     * Space complexity: O(n)
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());

        /* my first approach
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for ( int i = 0; i < n; i++ ){
            int crr = Integer.parseInt(st.nextToken());
            int crrCopy = crr;
            int count = 0;
            while(crr != 0){
                count += (crr & 1);
                crr >>= 1;
            }
            map.computeIfAbsent(count, k -> new TreeSet<>()).add(crrCopy);
        }
        boolean first = true;
        while(!map.isEmpty()){
            TreeSet<Integer> tempSet = map.get(map.lastKey());
            for (int num : tempSet){
                if (!first) out.print(" ");
                out.print(num);
                first = false;
            }
            map.remove(map.lastKey());
        }

         */

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list, (a,b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            if (countA!=countB) return Integer.compare(countA, countB) * -1;
            return Integer.compare(a, b);
        });
        for (int i = 0; i < list.size(); i++){
            if (i != 0) out.print(" ");
            out.print(list.get(i));
        }
        out.println();
        out.close();
    }
}
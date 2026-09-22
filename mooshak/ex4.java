import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.IOException;

class ex4 {
    /**
     * [PC004] Cool Sequences
     *
     * "You are given a sequence of positive integers of length N, S=(s1​,
     * s2​,...,sN) and your goal is to remove some elements so that it
     * becomes a cool sequence. In this problem, a sequence S is a cool
     * sequence if for all elements x in S, the value x appears exactly x
     * times in S For example, (3,3,3), (4,2,4,1,4,2,4) and () (an empty
     * sequence) are cool sequences, while (3,3,3,3) and (2,4,1,4,2) are not."
     *
     * Approach: I can use a HashMap to count the occurrences of each number
     * in the sequence and an auxiliar Set to keep track of the number that
     * are not cool yet, in case I have to remove all of them in the end I
     * won't be needing to iterate throughout the map again to find out the
     * uncool ones. Adding directly to the remove integer when passing the
     * number to be cool, and when getting to the amount needed it removes
     * from the set.
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param args Gives me the sequence of numbers
     * @throws IOException trust mooshak.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Long, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int remove = 0;
        Set<Long> toStraightOut = new HashSet<>();
        while (st.hasMoreTokens()) {
            long key = Long.parseLong(st.nextToken());
            if (map.containsKey(key)){
                int count = map.get(key)+1;
                map.put(key, count);
                if (count == key) toStraightOut.remove(key);
                else if (count > key) remove++;
            }
            else {
                map.put(key, 1);
                if (key!=1) toStraightOut.add(key);
            }
        }
        for (long key : toStraightOut){
            remove += map.get(key);
        }
        System.out.println(remove);
    }
}
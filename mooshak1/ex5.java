import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class ex5 {
    /**
     * [PC005] Conformity
     *
     * "Frosh commencing their studies at Waterloo have diverse interests,
     * as evidenced by their desire to take various combinations of courses
     * from among those available. University administrators are
     * uncomfortable with this situation, and therefore wish to offer a
     * conformity prize to frosh who choose the most popular combination of
     * courses. How many frosh will win the prize?"
     *
     * Approach: Using a key generated in long size to store up to a few
     * thousands of combinations in a hashmap, and then counting the most
     * popular combinantions and if they have the same amount using this to
     * multiply it and return the intended result
     *
     * @param args give me the frosh
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        while (n!=0){
            HashMap<Long,Integer> map = new HashMap<>();
            int mostCombination = 0;
            int howManyCombinationsMax = 0;
            for (int j = 0; j<n;j++){
                int [] quintet = new int[5];
                st = new StringTokenizer(br.readLine());
                for(int i=0;i<5;i++){
                    quintet[i] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(quintet);
                long key = 0;
                for (int i=0; i<5;i++){
                    key = (key << 12) | quintet[i];
                }
                int current = map.getOrDefault(key, 0) + 1;
                map.put(key, current);
                if (current > mostCombination){
                    mostCombination = current;
                    howManyCombinationsMax = 1;
                }
                else if (current == mostCombination){
                    howManyCombinationsMax++;
                }
            }
            System.out.println(howManyCombinationsMax * mostCombination);
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
        }
    }
}
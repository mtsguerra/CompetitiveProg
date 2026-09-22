import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

class ex1{
    /**
     *  [PC001] Increasing Array
     *
     *  "You are given an array of integers. You want to modify the array so
     *  that it is increasing, i.e., every element is at least as large as
     *  the previous element. On each move, you may increase the value of any
     *  element by one. What is the minimum number of moves required?"
     *
     *  ! Using stringTokenizer and BufferedReader for the first time !
     *
     *  Approach: For the most efficient solution, I can keep the highest
     *  value that I found in the interval, in such a way that this value
     *  will be responsible for setting the rule for all the subsequent
     *  values, so every number that is lower I can add just enough to get to
     *  the same value
     *
     *  Time complexity: O(n)
     *  Space complexity: O(1)
     *
     * @param args the number of numbers to evaluate and the minimum number
     *             of moves to solve it
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long currentHighest = Long.parseLong(st.nextToken());
        long numberOfMoves = 0;
        for (long i = 0; i < n-1; i++){
            if (!st.hasMoreTokens()){
                st = new StringTokenizer(br.readLine());
            }
            long current =  Long.parseLong(st.nextToken());
            if (current >= currentHighest){
                currentHighest = current;
            }
            else {
                numberOfMoves += currentHighest - current;
            }
        }
        System.out.println(numberOfMoves);
    }
}
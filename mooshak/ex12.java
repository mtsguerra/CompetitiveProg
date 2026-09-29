import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class ex12 {

    private static long howManyProd (long[] machines, long time){
        long currentMachines = 0;
        for (long n : machines) currentMachines += time / n;
        return currentMachines;
    }

    /**
     * [PC012] Factory Machines
     *
     * "A factory has n machines which can be used to make products. Your goal
     * is to make a total of t products. For each machine, you know the
     * number of seconds it needs to make a single product. The machines can
     * work simultaneously, and you can freely decide their schedule. What is
     * the shortest time needed to make t products?"
     *
     * Approach: Using a binary search, starting with the estimate of the max
     * time needed to make t products, by using the fastest machine.
     *
     * Time complexity: O(n log t)
     * Space complexity: O(1)
     *
     * @param args info
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int nMachines = Integer.parseInt(st.nextToken());
        int nProducts = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        long[] machines = new long[nMachines];
        for (int i = 0; i < nMachines; i++){
            machines[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(machines);

        long highTime = machines[0] * nProducts, lowTime = 0;
        long bestTime = highTime;

        while (highTime >= lowTime){
            long half = lowTime + (highTime - lowTime) / 2;
            long currentProds = howManyProd(machines, half);
            if (currentProds >= nProducts){
                bestTime = half;
                highTime = lowTime + (highTime - lowTime) / 2 - 1;
            }
            else lowTime = lowTime + (highTime - lowTime) / 2 + 1;
        }

        out.println(bestTime);
        out.close();
    }
}
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class ex8 {
    /**
     * [PC008] Traffic Lights
     *
     * "There is a street of length x whose positions are numbered 0,1,...x.
     * Initially there are no traffic lights, but n sets of traffic lights are
     * added to the street one after another. Your task is to calculate the
     * length of the longest passage without traffic lights after each addition."
     *
     * Approach: Using a Treeset I can keep track of the positions of the
     * semaphores, and when adding a new one, I can update the distances in
     * the Treemap.
     *
     * Time complexity: O(n log n)
     * Space complexity: O(n)
     *
     * @param args street len and the semaphors
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int lenSt = Integer.parseInt(st.nextToken());
        int nTfl = Integer.parseInt(st.nextToken());

        TreeSet<Integer> semaphors = new TreeSet<>();
        semaphors.add(0);
        semaphors.add(lenSt);

        TreeMap<Integer, Integer> distances = new TreeMap<>();
        distances.put(lenSt, 1);

        st = new StringTokenizer(in.readLine());

        for (int i = 0; i < nTfl; i++){
            int semaphor = Integer.parseInt(st.nextToken());
            if (semaphors.contains(semaphor)){
                out.print(distances.lastKey() + " ");
                continue;
            }

            int ceiling = semaphors.higher(semaphor);
            int floor = semaphors.lower(semaphor);
            semaphors.add(semaphor);


            distances.put(semaphor - floor,
                    distances.getOrDefault((semaphor - floor), 0)+1);
            distances.put(ceiling - semaphor,
                    distances.getOrDefault((ceiling - semaphor), 0)+1);

            if (distances.get(ceiling - floor) == 1) distances.remove(ceiling-floor);
            else distances.put(ceiling - floor, distances.get(ceiling - floor) - 1);
            out.print(distances.lastKey() + " ");
        }
        out.println();
        out.flush();
    }
}
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

class ex10 {

    /**
     * Modifies the given TreeMap of prices by either removing the specified
     * key or decreasing its associated value. If the key's associated value
     * is 1, the key is removed from the map. Otherwise, the value associated
     * with the key is decreased by 1.
     *
     * @param prices the TreeMap containing integer keys representing prices and integer values representing their counts
     * @param n      the integer key to be removed or decremented in the TreeMap
     */
    static void removeFromTree (TreeMap<Integer, Integer> prices, int n){
        if (prices.get(n) == 1) prices.remove(n);
        else prices.put(n, prices.get(n)-1);
    }

    /**
     * [PC010] Concert Tickets
     *
     * "There are n concert tickets available, each with a certain price.
     * Then, m customers arrive, one after another. Each customer announces the
     * maximum price they are willing to pay for a ticket, and after this,
     * they will get a ticket with the nearest possible price such that it
     * does not exceed the maximum price.
     *
     * Approach: By using a treemap I can keep track of the prices and their
     * counts. Also, I can easily access the highest lower value from the
     * prices if a certain price is not available.
     *
     * Time complexity: O(n log n)
     * Space complexity: O(n)
     *
     * @param args gives me the tickets and clients
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out =
            new PrintWriter(System.out, true);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int nTickets = Integer.parseInt(st.nextToken());
        int nClients = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        TreeMap<Integer, Integer> prices = new TreeMap<>();

        for (int i = 0; i < nTickets; i++) {
            int n = Integer.parseInt(st.nextToken());
            prices.put(n, prices.getOrDefault(n, 0)+1);
        }

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < nClients; i++) {
            int wanted = Integer.parseInt(st.nextToken());
            if (prices.containsKey(wanted)){
                removeFromTree(prices, wanted);
                out.println(wanted);
            }
            else if (prices.lowerKey(wanted) == null) out.println(-1);
            else{
                out.println(prices.lowerKey(wanted));
                removeFromTree(prices, prices.lowerKey(wanted));
            }
        }
        out.close();
    }
}
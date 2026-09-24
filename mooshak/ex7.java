import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class ex7 {
    /**
     *
     * [PC007] Lemmings Battle
     *
     * "The first line of input contains a single number N, representing the
     * number of test cases that follow (1<= N <= 100).
     * Each test case starts with a line with three space-separated integers,
     * B, SB and SG, representing respectively the number of battlefields
     * available, the number of lemmings in the green army and the number of
     * lemmings in the blue army (1<= B, SB, SG <= 100000). Than follow
     * exactly SG lines, each one with an integer indicating the power of
     * one single lemming of the green army, followed by SB lines, each one
     * with an integer indicating the power of one single lemming of the blue
     * army. This power is a positive integer smaller than 101. The lemmings
     * in each army do not need to come in any particular order."
     *
     * Approach: Using a heap to efficiently store the lemmings' powers, I can
     * always send the strongest one to the battlefields (as demanded) and
     * using an auxiliary list to temporarily store the lemmings' that did not
     * die on battlefields, adding them back to the heap later on. By the end
     * the emptied list would be the losing army.
     *
     * @param args gives me the number of test cases and armies and battlefields
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int nTests = Integer.parseInt(st.nextToken());

        for (int p = 0; p < nTests; p++) {
            st = new StringTokenizer(reader.readLine());
            int nBattlefields = Integer.parseInt(st.nextToken());
            int szGreenArmy = Integer.parseInt(st.nextToken());
            int szBlueArmy = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> greenArmy =
                    new PriorityQueue<>((a,b) -> Integer.compare(b,a));
            PriorityQueue<Integer> blueArmy =
                    new PriorityQueue<>((a,b) -> Integer.compare(b,a));

            for (int i = 0; i < szGreenArmy; i++) {
                // exception case: line with space
                st = new StringTokenizer(reader.readLine());
                greenArmy.add(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < szBlueArmy; i++) {
                blueArmy.add(Integer.parseInt(reader.readLine()));
            }

            while (!greenArmy.isEmpty() && !blueArmy.isEmpty()) {
                List<Integer> greenSurvivors = new ArrayList<>();
                List<Integer> blueSurvivors = new ArrayList<>();
                for (int i = 0; i < nBattlefields; i++) {
                    int greenSoldier = greenArmy.poll();
                    int blueSoldier = blueArmy.poll();
                    if (greenSoldier > blueSoldier) greenSurvivors.add(greenSoldier - blueSoldier);
                    else if (greenSoldier < blueSoldier) blueSurvivors.add(blueSoldier - greenSoldier);
                    if (greenArmy.isEmpty() || blueArmy.isEmpty()) break;
                }
                greenArmy.addAll(greenSurvivors);
                blueArmy.addAll(blueSurvivors);
            }
            if (greenArmy.isEmpty() && blueArmy.isEmpty())
                out.println("green and blue died");
            else if (blueArmy.isEmpty()){
                out.println("green wins");
                while (!greenArmy.isEmpty()) out.println(greenArmy.poll());
            }
            else {
                out.println("blue wins");
                while (!blueArmy.isEmpty()) out.println(blueArmy.poll());
            }
            if (p!=nTests-1) out.println();
        }
        out.close();
    }
}
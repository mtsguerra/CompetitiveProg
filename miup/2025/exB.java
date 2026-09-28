import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class exB {

    /**
     * B. The Dice of Destiny
     *
     * "Two settlers, John and Hans, unearth mysterious alien dice while
     * exploring the ruins of an old structure. They begin to gamble with
     * them, but the dice are oddly unbalanced. The colonists ask: which die
     * should John choose to guarantee the best chance of victory? John and
     * Hans are playing a game involving three dice. Although all dice are
     * 6-sided, they are not necessarily identical. The sides of each die are
     * described by a list of numbers. The game proceeds as follows:
     *
     *     First, John picks one of the three dice.
     *     Then, Hans picks one of the remaining two dice.
     *     They both roll their chosen die.
     *     If they roll the same number, they both re-roll their die.
     *     Otherwise, the winner is the player who rolled the highest number.
     *
     * If it is impossible for either player to win (i.e., the probability
     * that John's and Hans's rolls are always equal is 1), they do not
     * bother to re-roll indefinitely and no winner is declared.
     *
     * You need to determine if John can guarantee at least a 50% chance of
     * winning. John must choose a die that ensures that, for both of the two
     * remaining dice Hans might pick, John's probability of winning is at
     * least 1/2. If multiple dice satisfy this condition, John chooses the
     * one with the smallest index."
     *
     * Approach: First starts by storing the dice1, now, when reading dice
     * 2, I can check simultaneously how many times 1 wins over 2 and vice
     * versa, and at the time of dice 3 I don't need to store it, I can check
     * both wins and losses and at the end a basic check to see if there is a
     * winning dice.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param args dices
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        List<Integer> dice1 = new ArrayList<>();
        List<Integer> dice2 = new ArrayList<>();

        int won1ag2 = 0;
        int won2ag1 = 0;

        int won1ag3 = 0;
        int won3ag1 = 0;

        int won2ag3 = 0;
        int won3ag2 = 0;

        for (int i = 0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 6; j++){
                int crr = Integer.parseInt(st.nextToken());
                if (i == 0){
                    dice1.add(crr);
                }
                else if (i == 1){
                    dice2.add(crr);
                    for (int n : dice1){
                        if (n > crr) won1ag2++;
                        else if (n < crr) won2ag1++;
                    }
                }
                else {
                    for (int n : dice1){
                        if (n > crr) won1ag3++;
                        else if (n < crr) won3ag1++;
                    }
                    for (int n : dice2){
                        if (n > crr) won2ag3++;
                        else if (n < crr) won3ag2++;
                    }
                }
            }
        }

        if (won1ag2 == 0 && won2ag1 == 0
        || won1ag3 == 0 && won3ag1 == 0
        || won2ag3 == 0 && won3ag2 == 0){
                out.println("No dice");
                out.close();
                return;
        }

        boolean wins1against2 = ((double) won1ag2 / (won1ag2 + won2ag1)) >= 0.5;
        boolean wins1against3 = ((double) won1ag3 / (won1ag3 + won3ag1)) >= 0.5;
        boolean wins2against3 = ((double) won2ag3 / (won2ag3 + won3ag2)) >= 0.5;

        if (wins1against2 && wins1against3) out.println(1);
        else if (!wins1against2 && wins2against3) out.println(2);
        else if (!wins1against3 && !wins2against3) out.println(3);
        else out.println("No dice");

        out.close();
    }
}
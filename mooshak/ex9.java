import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

class ex9 {

    static int leftCount;
    static long leftSum;
    static int rightCount;
    static long rightSum;

    /**
     * Quick removal of a number from a TreeMap or decrease its count
     * @param tm treemap
     * @param num number in question
     */
    static void removingFromTree (TreeMap<Integer, Integer> tm, int num) {
        if (tm.get(num) == 1) tm.remove(num);
        else tm.put(num, tm.get(num)-1);
    }

    /**
     * Balance the trees in such a way that the difference between the number
     * of elements is at most 1, focusing on the left tree as the main and
     * smallest tree (stores the lower numbers and has the median as its
     * highest key) while the right tree is the largest tree (stores the higher
     * numbers)
     * @param leftTree lt
     * @param rightTree rt
     */
    static void balancingTrees(TreeMap<Integer, Integer> leftTree,
                               TreeMap<Integer, Integer> rightTree){
        if (leftCount == rightCount || leftCount == rightCount+1) return;
        if (leftCount > rightCount){
            int leftHigh = leftTree.lastKey();
            removingFromTree(leftTree, leftHigh);
            rightTree.put(leftHigh, rightTree.getOrDefault(leftHigh,0)+1);
            leftCount--;
            leftSum -= leftHigh;
            rightCount++;
            rightSum += leftHigh;
            return;
        }
        int rightLow = rightTree.firstKey();
        removingFromTree(rightTree, rightLow);
        leftTree.put(rightLow, leftTree.getOrDefault(rightLow,0)+1);
        leftCount++;
        leftSum += rightLow;
        rightCount--;
        rightSum -= rightLow;
    }

    /**
     * Add a number to the window, balancing the trees in the process
     * @param leftTree lt
     * @param rightTree rt
     * @param num number to add
     */
    static void addingToWindow (TreeMap<Integer, Integer> leftTree,
                                TreeMap<Integer, Integer> rightTree, int num){
        int rightLow = rightCount == 0 ? Integer.MAX_VALUE :
                rightTree.firstKey();
        if (num <= rightLow){
            leftTree.put(num, leftTree.getOrDefault(num,0)+1);
            leftSum += num;
            leftCount++;
        }
        else {
            rightTree.put(num, rightTree.getOrDefault(num,0)+1);
            rightSum += num;
            rightCount++;
        }
        balancingTrees(leftTree,rightTree);
    }

    /**
     * Check the window and return the difference between the sum of the numbers
     * in the window and the sum of the numbers in the window multiplied by the
     * number of elements in the window
     * @param leftTree lt
     * @param rightTree rt
     * @return the number of operations needed to balance the window
     */
    static long checkWindow (TreeMap<Integer, Integer> leftTree,
                            TreeMap<Integer, Integer> rightTree) {
        long mid = leftTree.lastKey();
        long diffLeft = mid *leftCount - leftSum;
        long diffRight = rightSum - mid *rightCount;
        return diffLeft + diffRight;
    }

    /**
     * Main method to run the program
     * @param args command line arguments
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int winSize = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());

        if (winSize == 1){
            for (int p=0; p<n; p++){
                out.print("0 ");
            }
            out.println();
            out.flush();
            return;
        }

        TreeMap<Integer, Integer> leftTree = new TreeMap<>();
        TreeMap<Integer, Integer> rightTree = new TreeMap<>();
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        leftTree.put(Math.min(n1,n2), 1);
        rightTree.put(Math.max(n1,n2), 1);
        leftSum = leftTree.lastKey();
        rightSum = rightTree.firstKey();
        leftCount = 1;
        rightCount = 1;
        Queue<Integer> enterOrder = new LinkedList<>();
        enterOrder.add(n1);
        enterOrder.add(n2);

        for (int i = 0; i < winSize-2; i++) {
            int num = Integer.parseInt(st.nextToken());
            enterOrder.add(num);
            addingToWindow(leftTree,rightTree,num);
        }

        out.print(checkWindow(leftTree,rightTree) + " ");

        for (int i = winSize; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            enterOrder.add(num);
            int toRemove = enterOrder.poll();
            int median = leftTree.lastKey();
            if (toRemove <= median){
                removingFromTree(leftTree,toRemove);
                leftSum -= toRemove;
                leftCount--;
            }
            else {
                removingFromTree(rightTree,toRemove);
                rightSum -= toRemove;
                rightCount--;
            }
            balancingTrees(leftTree, rightTree);
            addingToWindow(leftTree,rightTree,num);
            out.print(checkWindow(leftTree,rightTree) + " ");
        }
        out.println();
        out.flush();
    }
}
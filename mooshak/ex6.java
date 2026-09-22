import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class ex6{
    /**
     * [PC006] Playlist
     *
     * "You are given a playlist of a radio station since its establishment.
     * The playlist has a total of songs. What is the longest sequence of
     * successive songs where each song is unique?"
     *
     * Approach: using a two pointer array implemented on a sliding window
     * technic to keep track of the current sequence.
     *
     * @param args the sequence given
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int maxSize = 0;
        int left = 0;
        HashSet<Integer> set = new HashSet<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int right = 0; right < n; right++) {
            int current =  arr[right];
            while (set.contains(current)) {
                set.remove(arr[left]);
                left++;
            }
            set.add(current);
            maxSize = Math.max(maxSize, right - left + 1);
        }
        System.out.println(maxSize);
        /*
        while (st.hasMoreTokens()){
            int current = Integer.parseInt(st.nextToken());
            if (set.contains(current)){
                maxSize = Math.max(maxSize, q.size());
                while (!q.isEmpty()){
                    int currentPoll =  q.poll();
                    if (currentPoll == current) break;
                    set.remove(currentPoll);
                }
            }
            q.offer(current);
            set.add(current);
        }
        maxSize = Math.max(maxSize, q.size());
        System.out.println(maxSize);
         */
    }
}
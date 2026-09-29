import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class ex13 {

    private static double volume(double radius){
        return (double) (Math.PI * radius * radius);
    }

    private static boolean isPossible(double vol, List<Double> candidates,
                                     int nFriends){
        int totalFriends = 0;
        for (int i=0; i<candidates.size() && i < nFriends; i++){
            double candidate = candidates.get(i);
            totalFriends = (int) (totalFriends + candidate / vol);
        }
        return totalFriends >= nFriends;
    }

    /**
     * [PC013] Pie
     *
     * "My birthday is coming up and traditionally I'm serving pie. Not just
     * one pie, no, I have a number N of them, of various tastes and of
     * various sizes. F of my friends are coming to my party and each of them
     * gets a piece of pie. This should be one piece of one pie, not several
     * small pieces since that looks messy. This piece can be one whole pie
     * though. My friends are very annoying and if one of them gets a bigger
     * piece than the others, they start complaining. Therefore all of them
     * should get equally sized (but not necessarily equally shaped) pieces,
     * even if this leads to some pie getting spoiled (which is better than
     * spoiling the party). Of course, I want a piece of pie for myself too,
     * and that piece should also be of the same size. What is the largest
     * possible piece size all of us can get? All the pies are cylindrical in
     * shape and they all have the same height 1, but the radii of the pies
     * can be different."
     *
     * Approach: Using binary search to find the largest possible piece size
     * by starting with the lowest possible to give to all, in such a way
     * that all the pies (if nPies > nFriends, only the nFriends) can be
     * divided to find the largest possible piece size.
     *
     * @param args pies
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);
        int nTestCases = Integer.parseInt(in.readLine());
        for (int i=0; i<nTestCases; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int nPies = Integer.parseInt(st.nextToken());
            int nFriends = Integer.parseInt(st.nextToken())+1;

            st = new StringTokenizer(in.readLine());
            List<Double> volumesPies = new ArrayList<>();
            for (int j=0; j<nPies; j++){
                volumesPies.add(Double.parseDouble(st.nextToken()));
            }
            volumesPies.sort((a, b) -> Double.compare(a, b) * -1);
            for (int j=0; j<nPies && j<nFriends; j++){
                volumesPies.set(j, volume(volumesPies.get(j)));
            }
            double highestVolumeRad = volumesPies.get(0);
            double lowestVolumeRad =
                    volumesPies.get(nPies-1) / (1 + (nFriends > nPies ?
                            nFriends - nPies :
                            0));
            double bestVolumeRad = lowestVolumeRad;
            while (highestVolumeRad - lowestVolumeRad > 1e-4){
                double mid =
                        lowestVolumeRad + (highestVolumeRad - lowestVolumeRad) / 2;
                if (isPossible(mid, volumesPies, nFriends)){
                    bestVolumeRad = mid;
                    lowestVolumeRad = mid;
                }
                else {
                    highestVolumeRad = mid;
                }
            }
            out.printf(Locale.US, "%.4f\n", bestVolumeRad);
        }
        out.close();
    }
}
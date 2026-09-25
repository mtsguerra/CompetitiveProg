import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class exA {
    /**
     * A. Settlers of Nova Terra
     *
     * "The first wave of settlers has landed on Nova Terra and are building
     * colony outposts. Each outpost has an initial sequence of installed
     * modules, identified by a sequence of characters (a string a). The
     * supply convoy brings a sequence of new building modules (a string b)
     * that must be attached one-by-one (i.e., character by character) to the
     * initial sequence. Every new module is tagged with which team will
     * install it: the Vanguard team (marked 'V') always installs their
     * modules at the front of string a, while the Dock team (marked 'D')
     * always installs their module at the back of the string. Modules are
     * processed in the order they arrive, that is, in the order they appear
     * in string b."
     *
     * Approach: By using a string builder with the initial string, I can
     * iterate through the remaining characters and append or insert into the
     * initial suffix.
     *
     * @param args the strings and commands
     * @throws IOException trust mooshak
     */
    public static void main(String[] args) throws IOException{
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++){
            StringBuilder sufix = new StringBuilder();
            st = new StringTokenizer(in.readLine());
            sufix.append(st.nextToken());
            String temp = st.nextToken();
            String orders = st.nextToken();
            for (int j=0; j<orders.length(); j++){
                if (orders.charAt(j) == 'D') sufix.append(temp.charAt(j));
                else sufix.insert(0, temp.charAt(j));
            }
            out.println(sufix);
        }
        out.close();
    }
}
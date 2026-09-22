import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex1627A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalCases = Integer.parseInt(st.nextToken());
        for (int l = 0; l < totalCases; l++){
            st = new StringTokenizer(br.readLine());
            int rows = Integer.parseInt(st.nextToken());
            int cols = Integer.parseInt(st.nextToken());
            int rowToChange = Integer.parseInt(st.nextToken());
            int colToChange = Integer.parseInt(st.nextToken());

            boolean theresBlack = false;
            boolean foundASolution = false;
            boolean alreadyBlack = false;

            for (int i = 0; i < rows; i++){
                String currentLine = br.readLine();
                if (alreadyBlack) continue;
                if (foundASolution && i >= rowToChange) continue;

                // in the row
                if (i == rowToChange-1){
                    if (currentLine.charAt(colToChange-1) == 'B'){
                        alreadyBlack = true;
                        continue;
                    }
                    for (char ch :  currentLine.toCharArray()){
                        if (ch == 'B'){
                            foundASolution = true;
                            break;
                        }
                    }
                }

                // outside the row
                else {
                    if (!theresBlack){
                        for (char ch :  currentLine.toCharArray()){
                            if (ch == 'B'){
                                theresBlack = true;
                                break;
                            }
                        }
                    }
                    if (currentLine.charAt(colToChange-1) == 'B') foundASolution = true;
                }
            }
            if (alreadyBlack) System.out.println(0);
            else if (foundASolution) System.out.println(1);
            else if (theresBlack) System.out.println(2);
            else System.out.println(-1);
        }
    }
}
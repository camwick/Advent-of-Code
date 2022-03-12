import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution2 {

    public static void main(String[] args) {
        // get input
        String line = "";
        if (args.length == 0) {
            try {
                File file = new File("input.txt");
                Scanner input;
                input = new Scanner(file);
                line = input.nextLine();
                input.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            line = args[0];
        }

        // Solution
        int half = line.length() / 2;

        line += line;

        int ans = 0;

        for (int i = 0; i < line.length() / 2; ++i) {
            if (line.charAt(i) == line.charAt(i + half))
                ans += Character.getNumericValue(line.charAt(i));
        }

        System.out.println("Sum: " + ans);
    }
}
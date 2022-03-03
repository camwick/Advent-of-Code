import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            // file stuff
            File f = new File("input.txt");

            // scanner
            Scanner scan = new Scanner(f);
            String input = scan.nextLine();
            scan.close();

            // solution
            int ans = 0;

            for (int i = 0; i < input.length(); ++i) {
                switch (input.charAt(i)) {
                    case '(':
                        ans++;
                        break;
                    case ')':
                        ans--;
                        break;
                }
            }

            System.out.println(ans);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
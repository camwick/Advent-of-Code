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
            int ans1 = 0;
            int ans2 = 0;
            boolean notFound = true;

            for (int i = 0; i < input.length(); ++i) {
                switch (input.charAt(i)) {
                    case '(':
                        ans1++;
                        break;
                    case ')':
                        ans1--;
                        break;
                }

                if (notFound && ans1 == -1) {
                    ans2 = i + 1;
                    notFound = false;
                }
            }

            System.out.println(ans1 + "\n" + ans2);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
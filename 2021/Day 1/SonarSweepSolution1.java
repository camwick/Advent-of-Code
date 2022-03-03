import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SonarSweepSolution1 {
    public static void main(String[] args) {
        try {
            // grab file for input
            File file = new File("SonarSweepInput.txt");
            Scanner input = new Scanner(file);

            // variables used in while loop
            int counter = 0;
            int previous = Integer.valueOf(input.nextLine());
            int current;

            // loop through entire File Input
            while (input.hasNextLine()) {
                // grab next value from file
                current = Integer.valueOf(input.nextLine());

                // if current value is larger than previous ... increment counter
                if (current > previous)
                    counter++;

                // set up next previous comparison
                previous = current;
            }

            // print answer
            System.out.println(counter);

            // close Scanner
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class SonarSweepSolution2 {
    public static void main(String[] args) {
        try {
            // grab file for input
            File file = new File("SonarSweepInput.txt");
            Scanner input = new Scanner(file);

            // variables for while loop
            ArrayList<Integer> list = new ArrayList<>();
            int index = 0;

            // loop through file input values
            while (input.hasNextLine()) {
                // put file values into ArrayList list
                list.add(index, Integer.valueOf(input.nextLine()));
            }

            // variables for for loop
            ArrayList<Integer> sumsOfThree = new ArrayList<>();
            index = 0;

            // loop through ArrayList list
            for (int i = 0; i < list.size() - 2; ++i) {
                // add the next three values of ArrayList list
                // put answer into ArrayList sumsOfThree
                sumsOfThree.add(list.get(i) + list.get(i + 1) + list.get(i + 2));
            }

            // variables for for loop
            int counter = 0;
            int previous = sumsOfThree.get(sumsOfThree.size() - 1);

            // loop through ArrayList sumsOfThree
            for (int i = sumsOfThree.size() - 2; i >= 0; --i) {
                // if current value is larger than previous ... increment counter
                if (sumsOfThree.get(i) > previous)
                    counter++;

                // set up next previous comparison
                previous = sumsOfThree.get(i);
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
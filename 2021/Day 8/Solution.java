import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        if (args.length > 0)
            file = new File(args[0]);
        else
            file = new File("input.txt");

        input = new Scanner(file);

        // parse input
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        input.close();

        // PART 1
        long start = System.currentTimeMillis();

        int count = 0;
        for (int i = 0; i < lines.size(); ++i) {
            String[] parts = lines.get(i).split(" \\| ");
            String[] segments = parts[1].split(" ");

            for (String segment : segments) {
                switch (segment.length()) {
                    case 2:
                    case 3:
                    case 4:
                    case 7:
                        count++;
                }
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Count: " + count);
        System.out.println("Elapsed time: " + elapsed + "ms");

        // PART 2
        start = System.currentTimeMillis();

        elapsed = System.currentTimeMillis() - start;
        System.out.println("Sum: ");
        System.out.println("Elapsed time: " + elapsed + "ms");
    }
}
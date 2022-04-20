import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day6Customs {
    // private static int countYes() {

    // }

    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        if (args.length > 0)
            file = new File(args[0]);
        else
            file = new File("input.txt");
        input = new Scanner(file);

        // parsing input
        List<List<String>> list = new ArrayList<>();
        int index = 0;
        while (input.hasNextLine()) {
            String line = input.next();

            // move to next group
            if (line.equals("")) {
                index++;
                continue;
            }

            list.get(index).add(line);
        }
        System.out.println(list.size());
        input.close();

        // PART 1
        long start = System.nanoTime();

        int count = 0;

        long elapsed = System.nanoTime() - start;

        System.out.println("Sum of yes: " + count);
        System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms\n");
    }
}
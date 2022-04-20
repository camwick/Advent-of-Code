import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day3TobogganTraj {
    private static int checkSlope(List<String> list, int right, int down) {
        long start = System.nanoTime();
        int trees = 0;
        int x = 0, y = down;
        int maxX = list.get(0).length();
        int maxY = list.size();

        for (int i = down; i < maxY; i += down) {
            // calculate x
            if (x + right >= maxX) {
                x += right;
                x -= maxX;
            } else
                x += right;

            // see if we hit tree
            if (list.get(y).charAt(x) == '#')
                trees++;

            y += down;
        }

        return trees;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        if (args.length > 0)
            file = new File(args[0].trim());
        else
            file = new File("input.txt");

        input = new Scanner(file);

        List<String> list = new ArrayList<>();
        while (input.hasNextLine())
            list.add(input.nextLine());

        input.close();

        // PART 1
        long start = System.nanoTime();
        int treesHit = checkSlope(list, 3, 1);

        long elapsed = System.nanoTime() - start;
        System.out.println("trees hit: " + treesHit);
        System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "\n");

        // PART 2
        start = System.nanoTime();

        long slope11 = checkSlope(list, 1, 1);
        long slope31 = treesHit;
        long slope51 = checkSlope(list, 5, 1);
        long slope71 = checkSlope(list, 7, 1);
        long slope12 = checkSlope(list, 1, 2);
        long product = slope11 * slope31 * slope51 * slope71 * slope12;

        elapsed = System.nanoTime() - start;

        System.out.println("Product of tree hits: " + product);
        System.out.println("Elapsed Time: " + Duration.ofNanos(elapsed).toMillis());
    }
}
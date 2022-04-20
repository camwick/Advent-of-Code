import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day2PwPhilo {
    private static boolean isValid(String input) {
        int min, max, count = 0;
        char letter;
        String pw;

        // parsing input
        String[] parts = input.split(" ");
        String[] condition = parts[0].split("-");
        min = Integer.parseInt(condition[0]);
        max = Integer.parseInt(condition[1]);
        letter = parts[1].charAt(0);
        pw = parts[2];

        // count occurance of letter in pw
        for (int i = 0; i < pw.length(); ++i) {
            if (pw.charAt(i) == letter)
                count++;
        }

        // return true if count is between min and max
        return count >= min && count <= max;
    }

    private static boolean isValid2(String input) {
        int index1, index2;
        char letter;
        String pw;

        // parsing input
        String[] parts = input.split(" ");
        String[] conditions = parts[0].split("-");
        index1 = Integer.parseInt(conditions[0]) - 1;
        index2 = Integer.parseInt(conditions[1]) - 1;
        letter = parts[1].charAt(0);
        pw = parts[2];

        // check condition
        return (pw.charAt(index1) == letter || pw.charAt(index2) == letter)
                && !(pw.charAt(index1) == letter && pw.charAt(index2) == letter);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        // initializing file
        if (args.length > 0)
            file = new File(args[0].trim());
        else
            file = new File("input.txt");
        input = new Scanner(file);

        // getting input
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine())
            lines.add(input.nextLine());
        input.close();

        // PART 1
        long start = System.nanoTime();
        int valid = 0;
        for (int i = 0; i < lines.size(); ++i) {
            if (isValid(lines.get(i)))
                valid++;
        }
        long elapsed = System.nanoTime() - start;

        System.out.println("Valid: " + valid);
        System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms\n");

        // PART 2
        start = System.nanoTime();
        valid = 0;
        for (int i = 0; i < lines.size(); ++i) {
            if (isValid2(lines.get(i)))
                valid++;
        }
        elapsed = System.nanoTime() - start;

        System.out.println("Valid: " + valid);
        System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms");
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private static int square(int num) {
        return num * num;
    }

    private static int findFuelCost(List<Integer> positions, int target) {
        int sum = 0;

        for (int i = 0; i < positions.size(); ++i) {
            sum += Math.sqrt(square(positions.get(i) - positions.get(target)));
        }

        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        if (args.length > 0)
            file = new File(args[0]);
        else
            file = new File("input.txt");

        input = new Scanner(file);

        // parse input
        List<Integer> positions = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] numbers = line.split(",");

            for (String number : numbers)
                positions.add(Integer.parseInt(number));
        }
        input.close();

        // PART 1
        long start = System.currentTimeMillis();

        int minFuel = Integer.MAX_VALUE;
        for (int i = 0; i < positions.size(); ++i) {
            int fuel = findFuelCost(positions, i);
            if (fuel < minFuel)
                minFuel = fuel;
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Fuel spent: " + minFuel);
        System.out.println("Elapsed time: " + elapsed + "ms\n");

        // part 2
        start = System.currentTimeMillis();

        elapsed = System.currentTimeMillis() - start;
        System.out.println("Fuelt spent: ");
        System.out.println("Elapsed time: " + elapsed + "ms");
    }
}
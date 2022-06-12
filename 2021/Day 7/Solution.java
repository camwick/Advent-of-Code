import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private static int square(int num) {
        return num * num;
    }

    private static int maxInList(List<Integer> positions) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < positions.size(); ++i) {
            if (max < positions.get(i))
                max = positions.get(i);
        }

        return max;
    }

    private static int findFuelCost(List<Integer> positions, int target) {
        int sum = 0;

        for (int i = 0; i < positions.size(); ++i) {
            sum += Math.sqrt(square(positions.get(i) - positions.get(target)));
        }

        return sum;
    }

    private static int findIncFuelCost(List<Integer> positions, int target) {
        int sum = 0;

        for (int i = 0; i < positions.size(); ++i) {
            int difference = 0;
            difference += Math.sqrt(square(positions.get(i) - target));

            int fuelCost = (square(difference) + difference) / 2;
            sum += fuelCost;
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

        // PART 2
        start = System.currentTimeMillis();

        minFuel = Integer.MAX_VALUE;
        int listMax = maxInList(positions);
        for (int i = 0; i < listMax; ++i) {
            int fuel = findIncFuelCost(positions, i);
            if (fuel < minFuel)
                minFuel = fuel;
        }

        elapsed = System.currentTimeMillis() - start;
        System.out.println("Fuel spent: " + minFuel);
        System.out.println("Elapsed time: " + elapsed + "ms");
    }
}
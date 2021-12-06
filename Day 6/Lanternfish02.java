import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;

// NOTE
// NEEDED TO USE A LOT OF HELP TO GET THROUGH THIS PROBLEM

public class Lanternfish02 {
    public static void main(String[] args) throws FileNotFoundException {
        // variables
        File file = new File("input.txt");
        Scanner input = new Scanner(file);
        long start = System.currentTimeMillis();
        int maxDays = 256;
        Map<Integer, Long> agePools = new HashMap<>();

        // get line
        String line = input.nextLine();
        List<Integer> school = Arrays.stream(line.split(",")).toList().stream().map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int f : school) {
            agePools.compute(f, (a, v) -> v == null ? 1 : v + 1);
        }

        for (int d = 1; d <= maxDays; d++) {
            Map<Integer, Long> newDay = new HashMap<>();
            for (int age : agePools.keySet()) {
                newDay.put(age - 1, agePools.get(age));
                agePools.put(age, 0L);
            }
            long newFish = newDay.get(-1) == null ? 0 : newDay.get(-1);
            newDay.merge(8, newFish, Long::sum);
            newDay.merge(6, newFish, Long::sum);
            newDay.remove(-1);

            newDay.keySet().forEach(age -> agePools.put(age, newDay.get(age)));
        }

        long accum = agePools.values().stream().mapToLong(num -> num).sum();

        System.out.println("Fish: " + accum + " took: " + (System.currentTimeMillis() - start));

        input.close();
    }
}
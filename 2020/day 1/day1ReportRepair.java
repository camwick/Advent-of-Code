import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day1ReportRepair {
  /**
   * Searches given list for a number that equals 2020 when added to given start
   * index.
   * 
   * @param arr   integer list
   * @param start int, start index of search
   * @param end   int, end of list
   * @return int, number that satisfies arr.get(start) + x =2020
   */
  private static int searchSum(List<Integer> arr, int start, int end) {
    for (int i = start + 1; i < end; ++i) {
      if (arr.get(start) + arr.get(i) == 2020)
        return arr.get(i);
    }
    return -1;
  }

  /**
   * Searches given list for 2 numbers that equal 2020 when added together.
   * 
   * @param arr   integer list
   * @param start int, start index of search
   * @param end   int, end of list
   * @return String, the 3 numbers totalling 2020 when added.
   */
  private static String searchSum3(List<Integer> arr, int start, int end) {
    String output = "";
    for (int i = start + 1; i < end; ++i) {

      for (int j = start + 2; j < end; ++j) {
        if (arr.get(start) + arr.get(i) + arr.get(j) == 2020)
          return output += arr.get(start) + " " + arr.get(i) + " " + arr.get(j);
      }

    }
    return "no";
  }

  public static void main(String[] args) throws FileNotFoundException {
    // initializing scanner and list
    File file;
    if (args.length > 0)
      file = new File(args[0].trim());
    else
      file = new File("input.txt");

    Scanner input = new Scanner(file);
    List<Integer> list = new ArrayList<>();

    // adding input to list
    while (input.hasNextLine()) {
      list.add(input.nextInt());
    }
    input.close();

    // PART 1
    long start = System.nanoTime();
    int x = 0, y = 0;
    for (int i = 0; i < list.size(); ++i) {
      y = searchSum(list, i, list.size());

      if (y > 0) {
        x = list.get(i);
        break;
      }
    }
    long elapsed = System.nanoTime() - start;

    System.out.println("Part 1:");
    System.out.println(x + " + " + y + " = " + (x + y));
    System.out.println(x + " * " + y + " = " + (x * y));
    System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms\n");

    // PART 2
    start = System.nanoTime();
    String ans = "";
    for (int i = 0; i < list.size(); ++i) {
      ans = searchSum3(list, i, list.size());

      if (!ans.equals("no"))
        break;
    }
    elapsed = System.nanoTime() - start;

    String[] ansParts = ans.split(" ");
    int sum = Integer.parseInt(ansParts[0]) + Integer.parseInt(ansParts[1]) + Integer.parseInt(ansParts[2]);
    int product = Integer.parseInt(ansParts[0]) * Integer.parseInt(ansParts[1]) * Integer.parseInt(ansParts[2]);
    System.out.println("Part 2:");
    System.out.println(ansParts[0] + " + " + ansParts[1] + " + " + ansParts[2] + " = " + sum);
    System.out.println(ansParts[0] + " * " + ansParts[1] + " * " + ansParts[2] + " = " + product);
    System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms");
  }
}

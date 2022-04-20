import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day4PassportProc {
  private static boolean validitityCheck(List<String> arr) {
    for (String field : arr) {
      String[] parts = field.split(":");
      int year;

      switch (parts[0]) {
        case "byr": // birth year
          year = Integer.parseInt(parts[1]);

          if (!(year >= 1920 && year <= 2002))
            return false;

          break;
        case "iyr": // issue year
          year = Integer.parseInt(parts[1]);

          if (!(year >= 2010 && year <= 2020))
            return false;

          break;
        case "eyr": // expiration year
          year = Integer.parseInt(parts[1]);

          if (!(year >= 2020 && year <= 2030))
            return false;

          break;
        case "hgt": // height
          int num;
          if (parts[1].length() == 2)
            num = Integer.parseInt(parts[1]);
          else
            num = Integer.parseInt(parts[1].substring(0, parts[1].length() - 2));
          String label = parts[1].substring(parts[1].length() - 2);

          if (!(label.equals("cm") || label.equals("in")))
            return false;

          if (!(label.equals("cm") && num >= 150 && num <= 193) && !(label.equals("in") && num >= 59 && num <= 76))
            return false;

          break;
        case "hcl": // hair color
          if (!(parts[1].matches("(#)(([0-9]|[a-f]){6})")))
            return false;

          break;
        case "ecl": // eye color
          if (!(parts[1].matches("(amb|blu|brn|gry|grn|hzl|oth){1}")))
            return false;

          break;
        case "pid": // passpord id
          if (!(parts[1].length() == 9))
            return false;
          break;
      }
    }

    return true;
  }

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
    while (input.hasNextLine()) {
      List<String> arr = new ArrayList<>();
      while (input.hasNextLine()) {
        String line = input.nextLine();

        if (line.equals(""))
          break;

        String[] parts = line.split(" ");
        for (String x : parts)
          arr.add(x);
      }
      list.add(arr);
    }
    input.close();

    // PART 1
    long start = System.nanoTime();
    int valid = 0;
    for (List<String> arr : list) {
      if (arr.size() == 8)
        valid++;
      else if (arr.size() == 7) {
        boolean cidFound = false;

        // check for cid
        for (int i = 0; i < arr.size(); ++i) {
          if (arr.get(i).substring(0, 3).equals("cid"))
            cidFound = true;
        }

        if (!cidFound)
          valid++;
      }
    }
    long elapsed = System.nanoTime() - start;

    System.out.println("Part 1 Answer:");
    System.out.println("Valid passports: " + valid);
    System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms\n");

    // PART 2
    start = System.nanoTime();
    valid = 0;
    for (List<String> arr : list) {
      if (arr.size() == 8 && validitityCheck(arr))
        valid++;
      else if (arr.size() == 7 && validitityCheck(arr)) {
        boolean cidFound = false;

        // check for cid
        for (int i = 0; i < arr.size(); ++i) {
          if (arr.get(i).substring(0, 3).equals("cid"))
            cidFound = true;
        }

        if (!cidFound)
          valid++;
      }
    }
    elapsed = System.nanoTime() - start;

    System.out.println("Part 2 Answer:");
    System.out.println("Valid passports: " + valid);
    System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms");
  }
}

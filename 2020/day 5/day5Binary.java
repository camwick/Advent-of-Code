import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class day5Binary {
  public static void main(String[] args) throws FileNotFoundException {
    File file;
    Scanner input;

    if (args.length > 0)
      file = new File(args[0]);
    else
      file = new File("input.txt");

    input = new Scanner(file);

    // parsing input
    List<String> list = new ArrayList<>();
    while (input.hasNextLine()) {
      list.add(input.nextLine());
    }
    input.close();

    // PART 1
    long start = System.nanoTime();
    int max = 0;
    List<Integer> seats = new ArrayList<>();
    for (String boardingPass : list) {
      String row = boardingPass.substring(0, boardingPass.length() - 3);
      String column = boardingPass.substring(boardingPass.length() - 3);

      // parse binary for row
      String rowBin = "";
      for (int i = 0; i < row.length(); ++i) {
        if (row.charAt(i) == 'F')
          rowBin += "0";
        else
          rowBin += "1";
      }

      String columnBin = "";
      for (int i = 0; i < column.length(); ++i) {
        if (column.charAt(i) == 'L')
          columnBin += "0";
        else
          columnBin += "1";
      }

      // convert to binary + get seat id
      int binaryRow = Byte.parseByte(rowBin, 2);
      int binaryColumn = Byte.parseByte(columnBin, 2);
      int ans = binaryRow * 8 + binaryColumn;

      seats.add(ans);
      if (ans > max)
        max = ans;
    }
    long elapsed = System.nanoTime() - start;

    System.out.println("Part 1 Answer:");
    System.out.println("Highest seat ID: " + max);
    System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms\n");

    // Part 2
    start = System.nanoTime();
    Collections.sort(seats);
    int mySeat = 0;
    for (int i = 0; i < seats.size() - 1; ++i)
      if (seats.get(i) + 1 != seats.get(i + 1))
        mySeat = seats.get(i) + 1;
    elapsed = System.nanoTime() - start;

    System.out.println("Part 2 Answer:");
    System.out.println("My seat ID: " + mySeat);
    System.out.println("Elapsed time: " + Duration.ofNanos(elapsed).toMillis() + "ms");
  }
}

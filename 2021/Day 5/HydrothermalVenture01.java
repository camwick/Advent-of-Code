import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class HydrothermalVenture01 {
  public static class Line {
    final int x1;
    final int x2;
    final int y1;
    final int y2;

    public Line(int x1, int x2, int y1, int y2) {
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
    }

    public char getCommonVariable() {
      if (this.x1 == this.x2)
        return 'x';
      else
        return 'y';
    }

    public int getSmallerX() {
      return this.x1 > this.x2 ? this.x2 : this.x1;
    }

    public int getLargerX() {
      return this.x1 > this.x2 ? this.x1 : this.x2;
    }

    public int getSmallerY() {
      return this.y1 > this.y2 ? this.y2 : this.y1;
    }

    public int getLargerY() {
      return this.y1 > this.y2 ? this.y1 : this.y2;
    }

    public String toString() {
      return "" + this.x1 + ", " + this.y1 + " -> " + this.x2 + ", " + this.y2;
    }
  }

  private static void removeUnneededLines(ArrayList<Line> lines) {
    for (int i = 0; i < lines.size(); ++i) {
      if (!(lines.get(i).x1 == lines.get(i).x2 || lines.get(i).y1 == lines.get(i).y2)) {
        lines.remove(i);
        --i;
      }
    }
  }

  private static int findMaxX(ArrayList<Line> lines) {
    int max = 0;

    for (Line x : lines) {
      if (x.x1 >= max)
        max = x.x1;

      if (x.x2 >= max)
        max = x.x2;
    }

    return max;
  }

  private static int findMaxY(ArrayList<Line> lines) {
    int max = 0;

    for (Line x : lines) {
      if (x.y1 >= max)
        max = x.y1;

      if (x.y2 >= max)
        max = x.y2;
    }

    return max;
  }

  private static void addLine(int[][] diagram, Line line) {
    char commonVariable = line.getCommonVariable();

    if (commonVariable == 'x') {
      for (int y = line.getSmallerY(); y <= line.getLargerY(); ++y) {
        diagram[y][line.x1]++;
      }
    } else {
      for (int x = line.getSmallerX(); x <= line.getLargerX(); ++x) {
        diagram[line.y1][x]++;
      }
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("input.txt");
    Scanner input = new Scanner(file);

    ArrayList<Line> lines = new ArrayList<>();
    while (input.hasNextLine()) {
      String line = input.nextLine();

      String[] lineParts = line.trim().split(",| -> ");

      lines.add(new HydrothermalVenture01.Line(Integer.valueOf(lineParts[0]), Integer.valueOf(lineParts[2]),
          Integer.valueOf(lineParts[1]), Integer.valueOf(lineParts[3])));
    }

    removeUnneededLines(lines);

    int[][] diagram = new int[findMaxY(lines) + 1][findMaxX(lines) + 1];

    for (Line line : lines) {
      addLine(diagram, line);
    }

    int counter = 0;
    for (int y = 0; y < diagram.length; ++y) {
      for (int x = 0; x < diagram[y].length; ++x) {
        if (diagram[y][x] > 1)
          counter++;
      }
    }

    System.out.println("Dangerous Areas: " + counter);

    input.close();
  }
}
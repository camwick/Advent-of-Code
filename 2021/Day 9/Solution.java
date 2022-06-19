import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        if (args.length > 0)
            file = new File(args[0]);
        else
            file = new File("input.txt");

        input = new Scanner(file);

        // parse input
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine())
            lines.add(input.nextLine());

        input.close();

        // convert to integer array
        int[][] heightmap = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); ++i) {
            for (int j = 0; j < lines.get(0).length(); ++j) {
                heightmap[i][j] = Character.getNumericValue(lines.get(i).charAt(j));
            }
        }

        // PART 1
        long start = System.currentTimeMillis();

        // acquire risk levels
        List<Integer> riskLevel = new ArrayList<>();
        for (int y = 0; y < heightmap.length; ++y) {
            for (int x = 0; x < heightmap[y].length; ++x) {
                // top
                if (y == 0) {
                    // handle corners first
                    if (x == 0 && heightmap[y][x] < heightmap[y][x + 1] && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                    else if (x == heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y + 1][x]) {
                        riskLevel.add(1 + heightmap[y][x]);
                        break; // end of row
                    }
                    // rest of top layer
                    else if (x > 0 && x < heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y][x + 1]
                            && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                }
                // bottom
                else if (y == heightmap.length - 1) {
                    // handle corners first
                    if (x == 0 && heightmap[y][x] < heightmap[y][x + 1] && heightmap[y][x] < heightmap[y - 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                    else if (x == heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y - 1][x]) {
                        riskLevel.add(1 + heightmap[y][x]);
                        break; // end of row
                    }
                    // rest of bottom layer
                    else if (x > 0 && x < heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y][x + 1]
                            && heightmap[y][x] < heightmap[y - 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                }
                // everything in between
                else {
                    if (x == 0 && heightmap[y][x] < heightmap[y][x + 1] && heightmap[y][x] < heightmap[y - 1][x]
                            && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                    else if (x == heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y - 1][x] && heightmap[y][x] < heightmap[y + 1][x]) {
                        riskLevel.add(1 + heightmap[y][x]);
                        break; // end of row
                    } else if (x > 0 && x < heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y][x + 1]
                            && heightmap[y][x] < heightmap[y - 1][x] && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                }
            }
        }

        // sum of risk levels
        int riskLevelSum = 0;
        for (int x : riskLevel)
            riskLevelSum += x;

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Sum of risk levels: " + riskLevelSum);
        System.out.println("Elapsed time: " + elapsed + "ms\n");

        // PART 2
        start = System.currentTimeMillis();

        elapsed = System.currentTimeMillis() - start;
        System.out.println("PART 2 ANS: ");
        System.out.println("Elapsed time: " + elapsed + "ms");
    }
}
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

        // PART 2
    }
}
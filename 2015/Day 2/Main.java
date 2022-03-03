import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static int surfaceArea(int[] dimensions, int[] area) {
        int surfaceArea = 0;

        area[0] = dimensions[0] * dimensions[1];
        area[1] = dimensions[1] * dimensions[2];
        area[2] = dimensions[0] * dimensions[2];

        surfaceArea = 2 * area[0] + 2 * area[1] + 2 * area[2];

        return surfaceArea;
    }

    private static int minArea(int[] area) {
        int min = area[0];

        for (int i = 1; i < area.length; ++i)
            if (min >= area[i])
                min = area[i];

        return min;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scan = new Scanner(file);

            int ans1 = 0;
            String[] parts = new String[3];
            while (scan.hasNextLine()) {
                parts = scan.nextLine().split("x");

                // converting to integers
                int[] dimensions = new int[parts.length];
                for (int i = 0; i < dimensions.length; ++i)
                    dimensions[i] = Integer.parseInt(parts[i]);

                int[] area = new int[3];
                ans1 += surfaceArea(dimensions, area);
                ans1 += minArea(area);
            }

            System.out.println(ans1);

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
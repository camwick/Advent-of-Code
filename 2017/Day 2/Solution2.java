import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {
    private static int findDivisible(int[] arr, int index, int x) {
        for (int i = 0; i < arr.length; ++i) {
            if (i == index)
                continue;

            if (arr[i] % x == 0)
                return arr[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner input = new Scanner(file);

            int ans = 0;
            while (input.hasNextLine()) {
                // convert row into integer array
                String[] line = input.nextLine().trim().split("\\s+");
                int[] x = new int[line.length];
                for (int i = 0; i < x.length; ++i)
                    x[i] = Integer.parseInt(line[i]);

                // sort array cuz why not
                Arrays.sort(x);

                for (int i = 0; i < x.length; ++i) {
                    int dividend = findDivisible(x, i, x[i]);

                    if (dividend != -1) {
                        ans += dividend / x[i];
                        continue;
                    }
                }
            }
            input.close();

            System.out.println(ans);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
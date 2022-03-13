import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner input = new Scanner(file);

            int checkSum = 0;
            while (input.hasNextLine()) {
                String[] xStr = input.nextLine().trim().split("\\s+");

                int[] x = new int[xStr.length];

                for (int i = 0; i < x.length; ++i)
                    x[i] = Integer.parseInt(xStr[i]);

                Arrays.sort(x);
                checkSum += x[x.length - 1] - x[0];

            }
            input.close();

            System.out.println(checkSum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
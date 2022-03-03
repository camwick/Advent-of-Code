import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiveSolution1 {
    public static void main(String[] args) {
        try {
            File file = new File("DiveInput.txt");
            Scanner input = new Scanner(file);

            int x = 0;
            int y = 0;
            while (input.hasNextLine()) {
                String[] lineParts = input.nextLine().split(" ");

                switch (lineParts[0]) {
                    case "forward":
                        x += Integer.valueOf(lineParts[1]);
                        break;
                    case "down":
                        y += Integer.valueOf(lineParts[1]);
                        break;
                    case "up":
                        y -= Integer.valueOf(lineParts[1]);
                        break;
                }
            }

            System.out.println(x * y);

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
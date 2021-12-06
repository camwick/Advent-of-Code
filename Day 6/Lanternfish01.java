import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Lanternfish01 {
    public static class Lanternfish {
        public int age;

        public Lanternfish(int x) {
            this.age = x;
        }

        public boolean growOld() {
            if (this.age == 0) {
                this.age = 6;
                return true;
            } else {
                --this.age;
                return false;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        final int DAYS = 80;
        Scanner input = new Scanner(file);

        ArrayList<Lanternfish> fish = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] lineParts = line.trim().split(",");

            for (String x : lineParts) {
                fish.add(new Lanternfish01.Lanternfish(Integer.valueOf(x)));
            }
        }

        System.out.println("Initial amounmt: " + fish.size());

        int counter = 1;
        for (int i = DAYS; i > 0; --i) {
            int newFish = 0;

            for (int j = 0; j < fish.size(); ++j) {
                if (fish.get(j).growOld())
                    newFish++;
            }

            for (int u = 0; u < newFish; ++u) {
                fish.add(new Lanternfish01.Lanternfish(8));
            }

            System.out.println("After " + counter + " days: " + fish.size());
            counter++;
        }

        input.close();
    }
}
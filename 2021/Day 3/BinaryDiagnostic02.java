import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class BinaryDiagnostic02 {
    public static void removeMostCommon(ArrayList<Integer> binary, int bit, int length) {
        if (binary.size() == 1)
            return;

        int count = 0;
        for (int i = 0; i < binary.size(); ++i) {
            count += binary.get(i) >> bit & 1;
        }

        count = count / (double) binary.size() >= 0.5 ? 1 : 0;

        for (int i = 0; i < binary.size(); ++i) {
            if ((binary.get(i) >> bit & 1) != count) {
                binary.remove(i);
                --i;
            }
        }
    }

    public static void removeLeastCommon(ArrayList<Integer> binary, int bit, int length) {
        if (binary.size() == 1)
            return;

        int count = 0;
        for (int i = 0; i < binary.size(); ++i) {
            count += binary.get(i) >> bit & 1;
        }

        count = count / (double) binary.size() >= 0.5 ? 1 : 0;

        if (count == 1)
            count = 0;
        else
            count = 1;

        for (int i = 0; i < binary.size(); ++i) {
            if ((binary.get(i) >> bit & 1) != count) {
                binary.remove(i);
                --i;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner input = new Scanner(file);

        ArrayList<Integer> binary = new ArrayList<>();
        int binaryLength = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();

            binaryLength = line.length();
            binary.add(Integer.parseInt(line, 2));
        }

        ArrayList<Integer> copyBinary = new ArrayList<>(binary);
        System.out.println("length: " + copyBinary.size());
        for (int i = 0; i < binaryLength; ++i) {
            removeMostCommon(binary, binaryLength - 1 - i, binaryLength);
        }
        System.out.println("length: " + copyBinary.size());
        for (int i = 0; i < binaryLength; ++i) {
            removeLeastCommon(copyBinary, binaryLength - 1 - i, binaryLength);
        }

        System.out.println("Binary: ");
        for (int x : binary) {
            System.out.println(x);
        }
        System.out.println("length: " + copyBinary.size());
        System.out.println("copyBinary: ");
        for (int x : copyBinary) {
            System.out.println(x);
        }

        System.out.println("Answer: " + binary.get(0) * copyBinary.get(0));

        input.close();
    }
}
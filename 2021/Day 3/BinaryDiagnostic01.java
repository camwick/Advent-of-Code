import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class BinaryDiagnostic01 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner input = new Scanner(file);

        ArrayList<Integer> binary = new ArrayList<>();
        int binaryLength = 0;
        int count = 0;
        while (input.hasNextLine()) {
            String temp = input.nextLine();
            binaryLength = temp.length();
            count++;
            binary.add(Integer.parseInt(temp, 2));
        }

        int[] gammaCalc = new int[binaryLength];
        for (int i = 0; i < binary.size(); ++i) {
            for (int j = 0; j < binaryLength; ++j) {
                gammaCalc[j] += (binary.get(i) >> (-j + binaryLength - 1)) & 1;
            }
        }

        String gamma = "";
        for (int i = 0; i < gammaCalc.length; ++i)
            gamma += (gammaCalc[i] / (double) count) >= 0.5 ? 1 : 0;

        String epsilon = "";
        for (int i = 0; i < gamma.length(); ++i)
            epsilon += gamma.charAt(i) == '1' ? "0" : "1";

        System.out.println("Gamma: " + gamma);
        System.out.println("Epsilon: " + epsilon);
        System.out.println("Power Consumption: " + Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));

        input.close();
    }
}
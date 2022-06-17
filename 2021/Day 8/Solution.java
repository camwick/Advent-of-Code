import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static String convertAnalog(String analog, HashMap<Integer, Character[]> patternMap) {
        String number = "";

        for (int i = 1; i <= 9; ++i) {
            Character[] key = patternMap.get(i);

            if (key.length == analog.length()) {
                boolean test = true;
                for (int j = 0; j < key.length; ++j) {
                    if (!analog.contains(String.valueOf(key[j])))
                        test = false;
                }

                if (test)
                    number += String.valueOf(i);
            }
        }

        if (number.equals(""))
            number += "0";

        return number;
    }

    private static void identify(String line, HashMap<Integer, Character[]> patternMap) {
        String parsedLine = line.substring(0, line.indexOf("|")).trim() + line.substring(line.indexOf("|") + 1);

        String[] parts = parsedLine.split(" ");

        // checking for 1, 4, 7, 8
        for (int i = 0; i < parts.length; ++i) {
            Character[] x;
            switch (parts[i].length()) {
                case 2:
                    if (!patternMap.containsKey(1)) {
                        x = new Character[parts[i].length()];
                        for (int j = 0; j < x.length; ++j) {
                            x[j] = parts[i].charAt(j);
                        }
                        patternMap.put(1, x);
                    }
                    break;
                case 3:
                    if (!patternMap.containsKey(7)) {
                        x = new Character[parts[i].length()];
                        for (int j = 0; j < x.length; ++j) {
                            x[j] = parts[i].charAt(j);
                        }
                        patternMap.put(7, x);
                    }
                    break;
                case 4:
                    if (!patternMap.containsKey(4)) {
                        x = new Character[parts[i].length()];
                        for (int j = 0; j < x.length; ++j) {
                            x[j] = parts[i].charAt(j);
                        }
                        patternMap.put(4, x);
                    }
                    break;
                case 7:
                    if (!patternMap.containsKey(8)) {
                        x = new Character[parts[i].length()];
                        for (int j = 0; j < x.length; ++j) {
                            x[j] = parts[i].charAt(j);
                        }
                        patternMap.put(8, x);
                    }
                    break;
            }
        }

        // determine difference between 1 and 4
        Character[] fourdiff = new Character[2];
        Character[] one = patternMap.get(1);
        Character[] four = patternMap.get(4);

        int index = 0;
        for (int i = 0; i < four.length; ++i) {
            if (four[i] != one[0] && four[i] != one[1]) {
                fourdiff[index] = four[i];
                index++;
            }
        }

        // checking for 2, 3, 5
        for (int i = 0; i < parts.length; ++i) {
            // distinguishing between 5 length
            Character[] x = new Character[5];
            if (parts[i].length() == 5) {
                if (parts[i].indexOf(one[0]) != -1 && parts[i].indexOf(one[1]) != -1) {
                    if (!patternMap.containsKey(3)) {
                        for (int j = 0; j < x.length; ++j)
                            x[j] = parts[i].charAt(j);
                        patternMap.put(3, x);
                    }
                } else if (parts[i].indexOf(fourdiff[0]) != -1 && parts[i].indexOf(fourdiff[1]) != -1) {
                    if (!patternMap.containsKey(5)) {
                        for (int j = 0; j < x.length; ++j)
                            x[j] = parts[i].charAt(j);
                        patternMap.put(5, x);
                    }
                } else {
                    if (!patternMap.containsKey(2)) {
                        for (int j = 0; j < x.length; ++j)
                            x[j] = parts[i].charAt(j);
                        patternMap.put(2, x);
                    }
                }
            }
        }

        // checking for 0, 6, 9
        for (int i = 0; i < parts.length; ++i) {
            Character[] x;

            // 9 or 6
            if (parts[i].length() == 6) {
                // 9
                if (parts[i].indexOf(four[0]) != -1 && parts[i].indexOf(four[1]) != -1
                        && parts[i].indexOf(four[2]) != -1 && parts[i].indexOf(four[3]) != -1) {
                    if (!patternMap.containsKey(9)) {
                        x = new Character[6];
                        for (int j = 0; j < x.length; ++j)
                            x[j] = parts[i].charAt(j);
                        patternMap.put(9, x);
                    }
                }
                // 6
                else if (parts[i].indexOf(fourdiff[0]) != -1 && parts[i].indexOf(fourdiff[1]) != -1) {
                    if (!patternMap.containsKey(6)) {
                        x = new Character[6];
                        for (int j = 0; j < x.length; ++j)
                            x[j] = parts[i].charAt(j);
                        patternMap.put(6, x);
                    }
                }
            }
        }
    }

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
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        input.close();

        // PART 1
        long start = System.currentTimeMillis();

        int count = 0;
        for (int i = 0; i < lines.size(); ++i) {
            String[] parts = lines.get(i).split(" \\| ");
            String[] segments = parts[1].split(" ");

            for (String segment : segments) {
                switch (segment.length()) {
                    case 2:
                    case 3:
                    case 4:
                    case 7:
                        count++;
                        break;
                }
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Count: " + count);
        System.out.println("Elapsed time: " + elapsed + "ms\n");

        // PART 2
        start = System.currentTimeMillis();

        int sum = 0;

        for (int i = 0; i < lines.size(); ++i) {
            // indentify pattern for given line
            HashMap<Integer, Character[]> patternMap = new HashMap<>();
            identify(lines.get(i), patternMap);

            // convert analog numbers using the pattern
            String[] parts = lines.get(i).split(" \\| ");
            String[] segments = parts[1].split(" ");
            String number = "";
            for (int j = 0; j < segments.length; ++j) {
                number += convertAnalog(segments[j], patternMap);
            }
            sum += Integer.parseInt(number);

            patternMap.clear();
        }

        elapsed = System.currentTimeMillis() - start;
        System.out.println("Sum: " + sum);
        System.out.println("Elapsed time: " + elapsed + "ms");
    }
}
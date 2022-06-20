import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;
        long start, elapsed;

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

        // PART 1
        {
            start = System.currentTimeMillis();

            Stack<Character> navChars;
            List<Character> errors = new ArrayList<>();
            boolean error = false;

            for (String line : lines) {
                navChars = new Stack<>();
                navChars.push(line.charAt(0));

                for (int i = 1; i < line.length(); ++i) {
                    char x = line.charAt(i);

                    if (navChars.empty()) {
                        navChars.push(x);
                        continue;
                    }

                    switch (navChars.peek()) {
                        case '(':
                            if (x == ']' || x == '}' || x == '>') {
                                errors.add(x);
                                error = true;
                                break;
                            } else if (x == ')')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;

                        case '[':
                            if (x == ')' || x == '}' || x == '>') {
                                errors.add(x);
                                error = true;
                                break;
                            } else if (x == ']')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;

                        case '{':
                            if (x == ')' || x == ']' || x == '>') {
                                errors.add(x);
                                error = true;
                                break;
                            } else if (x == '}')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;

                        case '<':
                            if (x == ')' || x == '}' || x == ']') {
                                errors.add(x);
                                error = true;
                                break;
                            } else if (x == '>')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;
                    }

                    // move to next line if error occured
                    if (error) {
                        error = false;
                        break;
                    }
                }
            }

            int points = 0;
            for (char x : errors) {
                switch (x) {
                    case ')':
                        points += 3;
                        break;
                    case ']':
                        points += 57;
                        break;
                    case '}':
                        points += 1197;
                        break;
                    case '>':
                        points += 25137;
                        break;
                }
            }

            elapsed = System.currentTimeMillis() - start;
            System.out.println("\nSyntax Error Score: " + points);
            System.out.println("Elapsed time: " + elapsed + "ms\n");
        }

        // PART 2
        {
            start = System.currentTimeMillis();

            elapsed = System.currentTimeMillis() - start;
            System.out.println("PART 2 ANS: ");
            System.out.print("Elapsed time: " + elapsed + "ms");
        }
    }
}
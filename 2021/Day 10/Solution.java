import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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

        List<Integer> skipIndex = new ArrayList<>();
        // PART 1
        {
            start = System.currentTimeMillis();

            Stack<Character> navChars;
            List<Character> errors = new ArrayList<>();
            boolean error = false;

            int index = 0;
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
                        skipIndex.add(index);
                        break;
                    }
                }
                index++;
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

            int index = 0;
            Stack<Character> navChars;
            List<String> completedLines = new ArrayList<>();
            for (String line : lines) {
                if (skipIndex.contains(index)) {
                    index++;
                    continue;
                }

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
                            if (x == ')')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;

                        case '[':
                            if (x == ']')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;

                        case '{':
                            if (x == '}')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;

                        case '<':
                            if (x == '>')
                                navChars.pop();
                            else
                                navChars.push(x);
                            break;
                    }
                }

                // find the needed characters to make the line valid
                String str = "";
                while (!navChars.empty()) {
                    char x = navChars.pop();

                    switch (x) {
                        case '(':
                            str += ")";
                            break;
                        case '[':
                            str += "]";
                            break;
                        case '{':
                            str += "}";
                            break;
                        case '<':
                            str += ">";
                            break;
                    }
                }
                completedLines.add(str);

                index++;
            }

            List<Long> scores = new ArrayList<>();
            for (String line : completedLines) {
                long score = 0;
                for (int i = 0; i < line.length(); ++i) {
                    char x = line.charAt(i);

                    switch (x) {
                        case ')':
                            score = (score * 5) + 1;
                            break;
                        case ']':
                            score = (score * 5) + 2;
                            break;
                        case '}':
                            score = (score * 5) + 3;
                            break;
                        case '>':
                            score = (score * 5) + 4;
                            break;
                    }
                }
                scores.add(score);
            }

            Collections.sort(scores);

            elapsed = System.currentTimeMillis() - start;
            System.out.println("Median Score: " + scores.get(scores.size() / 2));
            System.out.print("Elapsed time: " + elapsed + "ms\n");
        }
    }
}
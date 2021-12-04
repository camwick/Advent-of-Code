import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class GiantSquid01 {
    public static class Number {
        public int num;
        public boolean hit;

        public Number(int x) {
            this.num = x;
            this.hit = false;
        }

        public void hit() {
            this.hit = true;
        }
    }

    public static class Board {
        public int[][] board;

        public Board(int[] boardNumbers) {
            board = new int[5][5];

            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[i].length; ++j) {
                    board[i][j] = boardNumbers[(i * 5) + j];
                }
            }
        }

        public String toString() {
            String output = "";

            for (int i = 0; i < this.board.length; ++i) {
                for (int j = 0; j < this.board[i].length; ++j) {
                    if (String.valueOf(this.board[i][j]).length() == 1)
                        output += " " + this.board[i][j] + " ";
                    else
                        output += this.board[i][j] + " ";
                }
                output += "\n";
            }

            return output;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("exampleInput.txt");
        Scanner input = new Scanner(file);

        // grab drawn numbers list
        String[] nums = input.nextLine().split(",");

        // convert to integers
        int[] drawnNums = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            drawnNums[i] = Integer.valueOf(nums[i]);
        }

        // grab boards
        int counter = 0;
        int boardAmount = 0;
        ArrayList<Board> boards = new ArrayList<>();
        Board temp;
        while (input.hasNextLine()) {
            int[] tempBoard = new int[25];

            for (int i = 0; i < tempBoard.length; ++i) {
                tempBoard[i] = input.nextInt();
            }
            input.nextLine();

            boards.add(new GiantSquid01.Board(tempBoard));
        }

        for (Board x : boards) {
            System.out.println(x);
        }

        input.close();
    }
}
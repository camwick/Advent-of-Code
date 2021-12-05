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

        public boolean isHit() {
            return this.hit;
        }

        public int getNum() {
            return this.num;
        }

        public String toString() {
            return String.valueOf(this.num);
        }
    }

    public static class Board {
        public Number[][] board;
        public boolean win;

        public Board() {

        }

        public Board(int[] boardNumbers) {
            board = new Number[5][5];
            this.win = false;

            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[i].length; ++j) {
                    board[i][j] = new GiantSquid01.Number(boardNumbers[(i * 5) + j]);
                }
            }
        }

        public void hit(int num) {
            for (int i = 0; i < this.board.length; ++i) {
                for (int j = 0; j < this.board[i].length; ++j) {
                    if (this.board[i][j].getNum() == num) {
                        this.board[i][j].hit();
                        return;
                    }
                }
            }
        }

        public boolean checkBingo() {
            // checking rows
            for (int i = 0; i < this.board.length; ++i) {
                if (row(this.board[i])) {
                    this.win = true;
                    return true;
                }
            }

            // checking columns
            for (int i = 0; i < this.board[0].length; ++i) {
                int counter = 0;

                for (int j = 0; j < this.board.length; ++j) {
                    if (this.board[i][j].isHit())
                        counter++;
                }

                if (counter == 5) {
                    this.win = true;
                    return true;
                }
            }

            // // check diagonals
            // int counter = 0;
            // for (int i = 0; i < 5; ++i) {
            // if (this.board[i][i].isHit())
            // counter++;
            // }
            // if (counter == 5) {
            // this.win = true;
            // return true;
            // }

            // counter = 0;
            // for (int i = 4; i >= 0; --i) {
            // if (this.board[i][i].isHit())
            // counter++;
            // }
            // if (counter == 5) {
            // this.win = true;
            // return true;
            // }

            return false;
        }

        private boolean row(Number[] row) {
            int counter = 0;
            for (int i = 0; i < row.length; ++i) {
                if (row[i].isHit())
                    counter++;
            }

            return counter == 5 ? true : false;
        }

        public boolean getWinningBoard() {
            return this.win;
        }

        public int sumUnmarked() {
            int ans = 0;

            for (int i = 0; i < this.board.length; ++i) {
                for (int j = 0; j < this.board[i].length; ++j) {
                    if (!this.board[i][j].isHit())
                        ans += this.board[i][j].getNum();
                }
            }

            return ans;
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
        File file = new File("input.txt");
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

        boolean loop = false;
        int winningNum = 0;
        for (int j = 0; j < drawnNums.length; ++j) {
            for (int i = 0; i < boards.size(); ++i) {
                boards.get(i).hit(drawnNums[j]);
                counter++;
                if (boards.get(i).checkBingo()) {
                    loop = true;
                    winningNum = drawnNums[j];
                    break;
                }
            }

            if (loop)
                break;
        }

        Board winningBoard = new GiantSquid01.Board();
        for (Board x : boards) {
            if (x.getWinningBoard()) {
                winningBoard = x;
                break;
            }
        }

        System.out.println("Winning Board:\n" + winningBoard);
        System.out.println("Won on number: " + winningNum);
        System.out.println("\nBoard's score: " + winningBoard.sumUnmarked() * winningNum);

        input.close();
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class GiantSquid02 {
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
          board[i][j] = new GiantSquid02.Number(boardNumbers[(i * 5) + j]);
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
          if (this.board[j][i].isHit())
            counter++;
        }

        if (counter == 5) {
          this.win = true;
          return true;
        }
      }

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
    ArrayList<Board> boards = new ArrayList<>();
    while (input.hasNextLine()) {
      int[] tempBoard = new int[25];

      for (int i = 0; i < tempBoard.length; ++i) {
        tempBoard[i] = input.nextInt();
      }
      input.nextLine();

      boards.add(new GiantSquid02.Board(tempBoard));
    }

    for (Board x : boards) {
      System.out.println(x);
    }

    boolean loop = false;
    int count = 0;
    int winningNum = 0;
    int winningIndex = 0;

    for (int j = 0; j < drawnNums.length; ++j) {
      for (int i = 0; i < boards.size(); ++i) {
        if (boards.get(i).getWinningBoard())
          continue;

        boards.get(i).hit(drawnNums[j]);

        if (boards.get(i).checkBingo()) {
          count++;
          winningNum = drawnNums[j];

          if (count == boards.size()) {
            loop = true;
            winningIndex = i;
            break;
          }
        }
      }

      if (loop)
        break;
    }

    Board winningBoard = boards.get(winningIndex);

    System.out.println("Winning Board:\n" + winningBoard);
    System.out.println("Won on number: " + winningNum);
    System.out.println("\nBoard's score: " + winningBoard.sumUnmarked() * winningNum);

    input.close();
  }
}
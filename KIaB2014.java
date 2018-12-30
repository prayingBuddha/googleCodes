import java.util.Scanner;

public class super2048 {
  private int[][] board;
  private int[][] rotated;
  int size;

  private void setCoordinates(int x, int y, int n) {
    board[x][y] = n;
  }

  private super2048(int n) {
    size = n;
    board = new int[n][n];
  }

  private void rotateMatrixClock(int[][] arr) {
    rotated = new int[size][size];
    for (int j = 0; j < size; j++) {
      for (int i = size - 1; i >= 0; i--) {
        rotated[j][size - i - 1] = board[i][j];
      }
    }
    board = rotated;
  }

  private void rotateMatrixAntiClock(int[][] arr) {
    rotated = new int[size][size];
    for (int j = size - 1; j >= 0; j--) {
      for (int i = 0; i < size; i++) {
        rotated[size - j - 1][i] = board[i][j];
      }
    }
    board = rotated;
  }


  private void right() {
    for (int j = 0; j < size; j++) {
      for (int i = size - 1; i > 0; i--) {
        if (board[j][i] == 0) {
          for (int w = i; w >= 0; w--) {
            if (board[j][w] != 0) {
              board[j][i] = board[j][w];
              board[j][w] = 0;
              break;
            }
          }

        }
        int k = 1;
        while (k <= i) {
          if (board[j][i - k] != 0) {
            if (board[j][i - k] == board[j][i]) {
              // try {
              board[j][i] *= 2;
              board[j][i - k] = 0;
              for (int l = i - 1; l >= 0; l--) {
                try {
                  board[j][l] = board[j][l - 1];
                } catch (ArrayIndexOutOfBoundsException en) {
                  board[j][l] = 0;
                }
                ;
              }
              //  } catch (ArrayIndexOutOfBoundsException en) {};
            } else {
              if (k != 1) {
                board[j][i - 1] = board[j][i - k];
                board[j][i - k] = 0;
              }
            }
            break;
          }
          k++;
        }
      }
    }
  }

  public static void main(String []args) {
    Scanner input = new Scanner(System.in);
    int numberOfTestCases = input.nextInt();

    while (numberOfTestCases > 0) {
      int n = input.nextInt();
      String command = input.next();
      KIaB2014 mainObject = new KIaB2014(n);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          mainObject.setCoordinates(i, j, input.nextInt());
        }
      }



      if (command.equals("right")) {
        mainObject.right();
        mainObject.rotated = mainObject.board;
      } else if (command.equals("left")) {
        mainObject.rotateMatrixClock(mainObject.board);
        mainObject.rotateMatrixClock(mainObject.board);
        mainObject.right();
        mainObject.rotateMatrixClock(mainObject.board);
        mainObject.rotateMatrixClock(mainObject.board);
      } else if (command.equals("up")) {
        mainObject.rotateMatrixClock(mainObject.board);
        mainObject.right();
        mainObject.rotateMatrixAntiClock(mainObject.board);
      } else if (command.equals("down")) {
        mainObject.rotateMatrixAntiClock(mainObject.board);
        mainObject.right();
        mainObject.rotateMatrixClock(mainObject.board);
      }

      for (int i = 0; i < mainObject.size; i++) {
        for (int j = 0; j < mainObject.size; j++) {
          System.out.print(mainObject.rotated[i][j] + " ");
        }
        System.out.print('\n');
      }

      numberOfTestCases--;
    }
  }
}

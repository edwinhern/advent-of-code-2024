package Day04;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Day04 {
  private static final Logger LOGGER = Logger.getLogger(Day04.class.getName());
  private static final String KEYWORD = "XMAS";
  private static final int[][] DIRECTIONS = {
      { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 },
      { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }
  };

  public static void main(String[] args) {
    char[][] board = readInput();

    LOGGER.info(() -> "Part 1 Solution: " + part1(board));
    LOGGER.info(() -> "Part 2 Solution: " + part2(board));
  }

  private static long part1(char[][] board) {
    long counter = 0;
    int rows = board.length;
    int cols = board[0].length;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (board[i][j] == KEYWORD.charAt(0)) {
          counter += countValidDirections(board, i, j);
        }
      }
    }

    return counter;
  }

  private static long part2(char[][] board) {
    long count = 0;
    int rows = board.length;
    int cols = board[0].length;

    for (int i = 1; i < rows - 1; i++) {
      for (int j = 1; j < cols - 1; j++) {
        if (board[i][j] == 'A') { // Check for 'A' to be in the center
          // Check diagonal 1 (top-left to bottom-right)
          char topLeft = board[i - 1][j - 1];
          char bottomRight = board[i + 1][j + 1];
          boolean diagonal1 = hasValidDiagonal(topLeft, bottomRight) ||
              hasValidDiagonal(bottomRight, topLeft);

          // Check diagonal 2 (top-right to bottom-left)
          char topRight = board[i - 1][j + 1];
          char bottomLeft = board[i + 1][j - 1];
          boolean diagonal2 = hasValidDiagonal(topRight, bottomLeft) ||
              hasValidDiagonal(bottomLeft, topRight);

          // Only count if we have valid diagonals in BOTH directions
          if (diagonal1 && diagonal2) {
            count++;
          }
        }
      }
    }

    return count;
  }

  private static boolean hasValidDiagonal(char start, char end) {
    return start == 'M' && end == 'S'; // Checks for forward direction (M->A->S)
  }

  private static long countValidDirections(char[][] board, int row, int col) {
    long count = 0;
    for (int[] direction : DIRECTIONS) {
      if (isValidDirection(board, row, col, direction[0], direction[1])) {
        count++;
      }
    }

    return count;
  }

  private static boolean isValidDirection(char[][] board, int row, int col, int dirRow, int dirCol) {
    int rows = board.length;
    int cols = board[0].length;

    for (int i = 0; i < KEYWORD.length(); i++) {
      int newRow = row + i * dirRow;
      int newCol = col + i * dirCol;

      boolean isRowOutOfBound = newRow < 0 || newRow >= rows;
      boolean isColOutOfBound = newCol < 0 || newCol >= cols;
      if (isRowOutOfBound || isColOutOfBound || board[newRow][newCol] != KEYWORD.charAt(i)) {
        return false;
      }
    }

    return true;
  }

  private static char[][] readInput() {
    List<String> lines = new ArrayList<>();

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/Day04-Input.txt"))) {
      while (scanner.hasNextLine()) {
        lines.add(scanner.nextLine());
      }
    } catch (Exception e) {
      LOGGER.severe(() -> "Error reading input: " + e.getMessage());
      return new char[0][0];
    }

    char[][] board = new char[lines.size()][lines.get(0).length()];

    for (int i = 0; i < lines.size(); i++) {
      board[i] = lines.get(i).toCharArray();
    }

    return board;
  }
}

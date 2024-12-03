package day02;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {

  public static void main(String[] args) throws IOException {
    int safeReports = 0;

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/day02/input.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        List<Integer> levels = Arrays.stream(line.split(" "))
          .map(Integer::parseInt)
          .collect(Collectors.toList());

        if(isSafeReport(levels)) {
          safeReports++;
        }
      }
    }

    System.out.println(safeReports);
  }

  private static boolean isSafeReport(List<Integer> levels) {
    boolean isIncreasing = levels.get(1) > levels.get(0);

    for(int i = 1; i < levels.size(); i++) {
      int diff = levels.get(i) - levels.get(i - 1);

      // Validate if diff is within bounds (1-3) or if it's increasing or decreasing
      boolean isWithinBounds = diff == 0 || Math.abs(diff) > 3;
      boolean isIncreasingBounds = isIncreasing && diff < 0;
      boolean isDecreasingBounds = !isIncreasing && diff > 0;
      if(isWithinBounds || isIncreasingBounds || isDecreasingBounds) {
        return false;
      }
    }
    return true;
  }

}

package day02;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class Day02 {
  private static final Logger LOGGER = Logger.getLogger(Day02.class.getName());

  public static void main(String[] args) throws IOException {
    List<List<Integer>> reports = new ArrayList<>();

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/day02/input.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        List<Integer> levels = Arrays.stream(line.split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        reports.add(levels);
      }
    }

    LOGGER.info(() -> "Part 1 Solution: " + part1(reports));
  }

  private static int part1(List<List<Integer>> reports) {
    return (int) reports.stream()
        .filter(Day02::isSafeReport)
        .count();
  }

  private static boolean isSafeReport(List<Integer> levels) {
    boolean isIncreasing = levels.get(1) > levels.get(0);

    for (int i = 1; i < levels.size(); i++) {
      int diff = levels.get(i) - levels.get(i - 1);

      // Validate if diff is within bounds (1-3) or if it's increasing or decreasing
      boolean isWithinBounds = diff == 0 || Math.abs(diff) > 3;
      boolean isIncreasingBounds = isIncreasing && diff < 0;
      boolean isDecreasingBounds = !isIncreasing && diff > 0;
      if (isWithinBounds || isIncreasingBounds || isDecreasingBounds) {
        return false;
      }
    }
    return true;
  }

}

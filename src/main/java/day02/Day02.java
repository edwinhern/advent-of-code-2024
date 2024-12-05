package Day02;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day02 {
  private static final Logger LOGGER = Logger.getLogger(Day02.class.getName());

  public static void main(String[] args) throws IOException {
    List<List<Integer>> reports = new ArrayList<>();

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/Day02-Input.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        List<Integer> levels = Arrays.stream(line.split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        reports.add(levels);
      }
    }

    LOGGER.info(() -> "Part 1 Solution: " + part1(reports));

    LOGGER.info(() -> "Part 2 Solution: " + part2(reports));
  }

  private static int part1(List<List<Integer>> reports) {
    int safeReports = 0;

    for (List<Integer> levels : reports) {
      boolean isSafe = true;
      boolean isIncreasing = levels.get(1) > levels.get(0);

      for (int i = 1; i < levels.size(); i++) {
        int diff = levels.get(i) - levels.get(i - 1);

        if (isNotValidLevel(diff, isIncreasing)) {
          isSafe = false;
        }
      }

      if (isSafe) {
        safeReports++;
      }
    }

    return safeReports;
  }

  private static int part2(List<List<Integer>> reports) {
    int safeReports = 0;

    for (List<Integer> levels : reports) {
      if (isSequenceSafe(levels)) {
        safeReports++;
        continue;
      }

      for (int i = 0; i < levels.size(); i++) {
        List<Integer> modified = new ArrayList<>(levels);
        modified.remove(i);

        if (isSequenceSafe(modified)) {
          safeReports++;
          break;
        }
      }
    }

    return safeReports;
  }

  private static boolean isSequenceSafe(List<Integer> levels) {
    if (levels.size() < 2)
      return true;

    boolean isIncreasing = levels.get(1) > levels.get(0);

    for (int i = 1; i < levels.size(); i++) {
      int diff = levels.get(i) - levels.get(i - 1);

      if (isNotValidLevel(diff, isIncreasing)) {
        return false;
      }
    }

    return true;
  }

  private static boolean isNotValidLevel(int diff, boolean isIncreasing) {
    // If not increasing or decreasing, it's not a valid level
    if (diff == 0)
      return true;

    // If diff is greater than 3, it's not a valid level
    if (Math.abs(diff) > 3)
      return true;

    // If diff is increasing and less than 0, or diff is decreasing and greater than
    // 0, it's not a valid level
    return isIncreasing && diff < 0 || !isIncreasing && diff > 0;
  }
}

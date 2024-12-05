package Day03;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
  private static final Logger LOGGER = Logger.getLogger(Day03.class.getName());
  private static final Pattern PATTERN = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
  private static final Pattern PATTERN_2 = Pattern.compile("do\\(\\)");
  private static final Pattern PATTERN_3 = Pattern.compile("don't\\(\\)");

  public static void main(String[] args) throws IOException {
    List<String> lines = new ArrayList<>();

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/Day03-Input.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lines.add(line);
      }
    }

    LOGGER.info(() -> "Part 1 Solution: " + part1(lines));

    LOGGER.info(() -> "Part 2 Solution: " + part2(lines));
  }

  private static long part1(List<String> lines) {
    long sum = 0;

    for (String line : lines) {
      Matcher matcher = PATTERN.matcher(line);

      while (matcher.find()) {
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        sum += x * y;
      }
    }

    return sum;
  }

  private static long part2(List<String> lines) {
    Pattern allPatterns = Pattern.compile(PATTERN.pattern() + "|" + PATTERN_2.pattern() + "|" + PATTERN_3.pattern());

    long sum = 0;
    boolean doEnabled = true;

    for (String line : lines) {
      Matcher matcher = allPatterns.matcher(line);

      while (matcher.find()) {
        String match = matcher.group();

        if (PATTERN_3.matcher(match).matches()) {
          doEnabled = false;
        } else if (PATTERN_2.matcher(match).matches()) {
          doEnabled = true;
        } else if (doEnabled && PATTERN.matcher(match).matches()) {
          int x = Integer.parseInt(matcher.group(1));
          int y = Integer.parseInt(matcher.group(2));
          sum += x * y;
        }
      }
    }

    return sum;
  }
}

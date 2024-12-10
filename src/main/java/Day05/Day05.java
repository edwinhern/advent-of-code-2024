package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

public class Day05 {
  private static final Logger LOGGER = Logger.getLogger(Day05.class.getName());

  public static void main(String[] args) {
    LOGGER.info(() -> "Part 1 Solution: ");
    LOGGER.info(() -> "Part 2 Solution: ");
  }

  private long part1() {
    int result = 0;

    return result;
  }

  private static Map<Integer, Stack<Integer>> readInput() {
    Map<Integer, Stack<Integer>> result = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Process each line
        Integer[] pageOrder = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toArray(Integer[]::new);
        result.merge(pageOrder[0], new Stack<Integer>(Arrays.asList(pageOrder[1])), (oldValue, newValue) -> {
          oldValue.push(newValue.pop());
          return oldValue;
        });
      }

    } catch (Exception e) {
      LOGGER.severe(() -> "Error reading input: " + e.getMessage());
    }
    return result;
  }
}

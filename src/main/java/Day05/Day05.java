package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class Day05 {
  private static final Logger LOGGER = Logger.getLogger(Day05.class.getName());

  public static void main(String[] args) {
    LOGGER.info(() -> "Part 1 Solution: " + part1());
    LOGGER.info(() -> "Part 2 Solution: ");
  }

  private static long part1() {
    Input input = readInput();
    long sum = 0;

    System.out.println(input.beforeMap.size());
    System.out.println(input.updates.size());

    // Check each update sequence
    for (List<Integer> update : input.updates) {
      if (isValidOrder(update, input.beforeMap)) {
        int middleIndex = (int) Math.floor(update.size() / 2);
        sum += update.get(middleIndex);
      }
    }

    return sum;
  }

  private static boolean isValidOrder(List<Integer> sequence, Map<Integer, Set<Integer>> beforeMap) {
    // For each number in the sequence
    for (int i = 0; i < sequence.size(); i++) {
      int current = sequence.get(i);

      // Look at all numbers that come after current in the sequence
      for (int j = i + 1; j < sequence.size(); j++) {
        int later = sequence.get(j);

        // If later number requires current number to be before it,
        // sequence is valid because current comes before later
        if (beforeMap.containsKey(later) && beforeMap.get(later).contains(current)) {
          // Valid case: current comes before later as required
          continue;
        }

        // If current number requires later number to be before it,
        // sequence is invalid because later comes after currnent
        if (beforeMap.containsKey(current) && beforeMap.get(current).contains(later)) {
          return false;
        }
      }
    }

    return true;
  }

  private static Input readInput() {
    Input input = new Input();
    boolean parsingRules = true;

    try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day05-Input.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) {
          parsingRules = false;
          continue;
        }

        if (parsingRules) { // Parse rule section (X|Y)
          String[] parts = line.split("\\|");
          int before = Integer.parseInt(parts[0]);
          int after = Integer.parseInt(parts[1]);

          // Store: number -> set of numbers that must come before it
          input.beforeMap.computeIfAbsent(after, k -> new HashSet<>()).add(before);
        } else { // Parse updates section (comma-separated nums)
          List<Integer> sequenece = Arrays.stream(line.split(","))
              .map(String::trim)
              .map(Integer::parseInt)
              .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
          input.updates.add(sequenece);
        }
      }

    } catch (Exception e) {
      LOGGER.severe(() -> "Error reading input: " + e.getMessage());
    }
    return input;
  }

  private static class Input {
    Map<Integer, Set<Integer>> beforeMap = new HashMap<>();
    List<List<Integer>> updates = new ArrayList<>();
  }
}

package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class Day06 {
  private static final Logger LOGGER = Logger.getLogger(Day06.class.getName());
  private static final String INPUT_PATH = "src/main/resources/Day06-Input.txt";
  private static final int[][] DIRECTIONS = {
      { -1, 0 }, // UP
      { 0, 1 }, // RIGHT
      { 1, 0 }, // DOWN
      { 0, -1 } // LEFT
  };

  private static class Position {
    int x, y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof Position))
        return false;
      Position p = (Position) o;
      return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static void main(String[] args) {
    LOGGER.info(() -> "Part 1 Solution: " + part1());
  }

  private static int part1() {
    List<String> grid = readInput();

    // Step 1: Find where the guard is at
    // We can iterate through the board and check each column if that have a `^`
    Position start = findGuard(grid);
    if (start == null)
      return 0;

    Set<Position> visited = new HashSet<>();
    visited.add(start);

    int currentDir = 0; // start facing UP
    Position current = start;

    // Step 2: Use DP to move guard
    while (true) {
      Position next = new Position(current.x + DIRECTIONS[currentDir][0], current.y + DIRECTIONS[currentDir][1]);

      // Check if guard would leave area
      if (next.x < 0 || next.x >= grid.size() || next.y < 0 || next.y >= grid.get(0).length()) {
        break;
      }

      // If blocked, turn right
      if (grid.get(next.x).charAt(next.y) == '#') {
        currentDir = (currentDir + 1) % DIRECTIONS.length;
        continue;
      }

      // Move forward
      current = next;
      visited.add(current);
    }

    return visited.size();
  }

  private static Position findGuard(List<String> grid) {
    for (int i = 0; i < grid.size(); i++) {
      if (grid.get(i).contains("^")) {
        int j = grid.get(i).indexOf("^");
        return new Position(i, j);
      }
    }

    return null;
  }

  private static List<String> readInput() {
    List<String> grid = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH))) {
      String line;

      while ((line = reader.readLine()) != null) {
        grid.add(line);
      }
    } catch (Exception e) {
      LOGGER.severe(() -> "Error reading input: " + e.getMessage());
    }

    return grid;
  }
}

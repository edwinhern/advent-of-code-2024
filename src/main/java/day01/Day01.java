package day01;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Day01 {

  public static void main(String[] args) throws IOException {
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/day01/input.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("   ");
        left.add(lineScanner.nextInt());
        right.add(lineScanner.nextInt());

        lineScanner.close();
      }
    }
    
    // Part 1: Calculate total distance between sorted lists
    System.out.println("Part 1 Solution: " + part1(left, right));

    // Part 2: Calculate similarity score
    System.out.println("Part 2 Solution: " + part2(left, right));
  }

  private static int part1(List<Integer> left, List<Integer> right) {
    List<Integer> sortedLeft = new ArrayList<>(left);
    List<Integer> sortedRight = new ArrayList<>(right);
    Collections.sort(sortedLeft);
    Collections.sort(sortedRight);

    int totalDistance = 0;
    for(int i = 0; i < sortedLeft.size(); i++) {
      totalDistance += Math.abs(sortedLeft.get(i) - sortedRight.get(i));
    }

    return totalDistance;
  }

  private static int part2(List<Integer> left, List<Integer> right) {
    Map<Integer, Integer> rightMap = new HashMap<>();
    for(int num : right) {
       rightMap.merge(num, 1, Integer::sum);
    }

    int similarityScore = 0;
    for(int num : left) {
      if(rightMap.containsKey(num)) {
        similarityScore += num * rightMap.getOrDefault(num, 0);
      }
    }

    return similarityScore;
  }
}

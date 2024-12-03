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
    // read through the text file
    List<List<String>> records = new ArrayList<>();

    try (Scanner scanner = new Scanner(new FileReader("src/main/resources/day02/input.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        records.add(Arrays.asList(line));
      }
    }

    int safeReports = 0;
    for (List<String> record : records) {
      // Convert String[] -> Integer[]
      List<String> numStrs = record.get(0).split(" ");
      List<Integer> numbers = numStrs.stream().map(Integer::parseInt).toList();

      int start = 1;
      // Iterate numArr using while loop. Start at index 1
      while (start < numbers.size() - 1) {
        // Compare diff = Math.abs(numArr[i] - numArr[i])
        int diff = Math.abs(numbers.get(start) - numbers.get(start - 1));
        //  diff > 3 || diff < 1 || diff == 0; we need to break and skip
        if(diff == 0 || diff > 3 || diff < 1) {
          break;
        }

        start++;
        
        // numArr.size() - 1 === index; increment safe report
        if (start == numbers.size() - 1) {
          safeReports++;
        }
      }
    }

    System.out.println(safeReports);
  }
}

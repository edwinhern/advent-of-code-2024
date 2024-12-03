package day01;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Day01 {

  public static void main(String[] args) throws IOException, CsvValidationException {
    // read through the csv file
    // iterate and create left and right list
    List<List<String>> records = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/day01/input.csv"))) {
      String[] values = null;
      while ((values = csvReader.readNext()) != null) {
        records.add(Arrays.asList(values));
      }
    }

    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    for (List<String> record : records) {
      left.add(Integer.parseInt(record.get(0)));
      right.add(Integer.parseInt(record.get(1)));
    }

    // take first list and sort in asc
    // take second list and sort in asc
    Collections.sort(left);
    Collections.sort(right);

    // create sum variable to hold difference
    int sum = 0;
    // iterate through first list
    for (int i = 0; i < left.size(); i++) {
      // access left and right based on i
      // sum += Math.abs(left[i] - right[i])
      sum += Math.abs(left.get(i) - right.get(i));
    }

    System.out.println(sum);
  }
}

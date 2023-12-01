package se.johannesdahlgren.aoc23;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day1 {

  private final List<String> words = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

  public int sumOfCalibrationValues(String fileName) {
    List<String> calibrationDocument = FileReader.readFileAsLines(fileName);
    return calibrationDocument.stream()
        .map(this::getCalibrationValue)
        .reduce(0, Integer::sum);
  }

  public int sumOfCalibrationValuesWithWords(String fileName) {
    List<String> calibrationDocument = FileReader.readFileAsLines(fileName);
    return calibrationDocument.stream()
        .map(this::getCalibrationValueWithWords)
        .reduce(0, Integer::sum);
  }

  private int getCalibrationValue(String s) {
    Pattern pattern = Pattern.compile("\\d");
    Matcher matcher = pattern.matcher(s);
    StringBuilder sj = new StringBuilder();
    while (matcher.find()) {
      sj.append(s.charAt(matcher.start()));
    }
    String string = sj.toString();
    char firstMatch = string.charAt(0);
    char lastMatch = string.charAt(sj.length() - 1);
    return Integer.parseInt(firstMatch + String.valueOf(lastMatch));
  }

  private int getCalibrationValueWithWords(String s) {
    int first = getFirstDigit(s);
    int last = getLastDigit(s);
    return Integer.parseInt(first + String.valueOf(last));
  }

  private int getFirstDigit(String s) {
    for (int i = 0; i < s.length(); i++) {
      int c = getInt(s, i);
      if (c != -1) {
        return c;
      }
    }
    return 0;
  }

  private int getLastDigit(String s) {
    for (int i = s.length() - 1; i >= 0; i--) {
      int c = getInt(s, i);
      if (c != -1) {
        return c;
      }
    }
    return 0;
  }

  private int getInt(String s, int i) {
    char c = s.charAt(i);
    if (Character.isDigit(c)) {
      return c - '0';
    } else {
      for (int j = 0; j < words.size(); j++) {
        String word = words.get(j);
        if (s.substring(i).startsWith(word)) {
          return j + 1;
        }
      }
    }
    return -1;
  }

}

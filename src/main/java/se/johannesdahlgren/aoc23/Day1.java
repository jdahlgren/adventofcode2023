package se.johannesdahlgren.aoc23;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day1 {

  public int sumOfCalibrationValues(String fileName) {
    List<String> calibrationDocument = FileReader.readFileAsLines(fileName);
    return calibrationDocument.stream()
        .map(this::getCalibrationValue)
        .reduce(0, Integer::sum);
  }

  private int getCalibrationValue(String s) {
    Pattern pattern = Pattern.compile("\\d");
    Matcher matcher = pattern.matcher(s);
    StringBuilder sj = new StringBuilder();
    while (matcher.find()){
      sj.append(s.charAt(matcher.start()));
    }
    String string = sj.toString();
    char firstMatch = string.charAt(0);
    char lastMatch = string.charAt(sj.length() - 1);
    return Integer.parseInt(firstMatch + String.valueOf(lastMatch));
  }

}

package se.johannesdahlgren.aoc23;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day8 {

  private final String fileName;

  public Day8(String fileName) {

    this.fileName = fileName;
  }

  public int numberOfStepsToEnd() {
    List<String> input = FileReader.readFileAsLines(fileName);
    char[] rlInstructions = input.get(0).toCharArray();
    Map<String, RLMap> rlMaps = input.stream()
        .skip(2)
        .collect(Collectors.toMap(line -> line.substring(0, 3), line -> new RLMap(line.substring(7, 10), line.substring(12, 15))));

    String END_LOCATION = "ZZZ";
    String currentLocation = "AAA";
    int numberOfSteps = 0;
    while (!currentLocation.equals(END_LOCATION)) {
      int rlIndex = numberOfSteps % rlInstructions.length;
      if (rlInstructions[rlIndex] == 'R') {
        currentLocation = rlMaps.get(currentLocation).r;
      } else {
        currentLocation = rlMaps.get(currentLocation).l;
      }
      numberOfSteps++;
    }
    return numberOfSteps;
  }

  private record RLMap(String l, String r) {

  }
}

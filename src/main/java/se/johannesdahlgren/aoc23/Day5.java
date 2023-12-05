package se.johannesdahlgren.aoc23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day5 {

  private final String fileName;

  public Day5(String fileName) {
    this.fileName = fileName;
  }

  public long getLowestLocationNumber() {
    Almanac almanac = toAlmanac(FileReader.readFileToString(fileName));
    long lowestLocationNumber = Long.MAX_VALUE;
    for (Long seedNumber : almanac.seedNumbers) {
      long destination = seedNumber;
      for (AToBMap aToBMap : almanac.map) {
        for (AToB aToB : aToBMap.aToB()) {
          if (destination >= aToB.source && destination < aToB.source + aToB.range) {
            destination = destination + aToB.destination - aToB.source;
            break;
          }
        }
      }
      if (destination < lowestLocationNumber) {
        lowestLocationNumber = destination;
      }
    }
    return lowestLocationNumber;
  }

  private Almanac toAlmanac(String input) {
    String[] split = input.split("\\n\\n");
    List<Long> seedNumbers = Arrays.stream(split[0]
            .substring(split[0].indexOf(":") + 1)
            .split(" "))
        .map(String::trim)
        .filter(Predicate.not(String::isEmpty))
        .map(Long::parseLong)
        .toList();

    List<AToBMap> aToBMapList = new ArrayList<>();
    for (int i = 1; i < split.length; i++) {
      List<AToB> aToBList = new ArrayList<>();
      String[] subSplit = split[i].split("\\n");
      for (int j = 1; j < subSplit.length; j++) {
        List<Long> aToBNumbers = Arrays.stream(subSplit[j].split(" "))
            .map(String::trim)
            .filter(Predicate.not(String::isEmpty))
            .map(Long::parseLong)
            .toList();
        aToBList.add(new AToB(aToBNumbers.get(0), aToBNumbers.get(1), aToBNumbers.get(2)));
      }
      aToBMapList.add(new AToBMap(aToBList));
    }
    return new Almanac(seedNumbers, aToBMapList);
  }

  private record Almanac(List<Long> seedNumbers, List<AToBMap> map) {

  }

  private record AToBMap(List<AToB> aToB) {

  }

  private record AToB(long destination, long source, long range) {

  }
}

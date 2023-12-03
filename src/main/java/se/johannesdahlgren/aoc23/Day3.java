package se.johannesdahlgren.aoc23;

import java.util.ArrayList;
import java.util.List;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day3 {

  private final String fileName;

  public Day3(String fileName) {
    this.fileName = fileName;
  }

  public int sumOfPartNumbers() {
    List<List<Character>> engineSchematic = FileReader.readFileAsCharMatrix(fileName);
    List<Integer> partNumbers = new ArrayList<>();
    for (int i = 0; i < engineSchematic.size(); i++) {
      List<Character> line = engineSchematic.get(i);
      boolean isNumberAdjacentToSymbol = false;
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < line.size(); j++) {
        char c = line.get(j);
        isNumberAdjacentToSymbol = isNumberAdjacentToSymbol || isNumberAdjacentToSymbol(i, j, engineSchematic);
        if (!Character.isDigit(c) && isNumberAdjacentToSymbol && !sb.isEmpty()) {
          partNumbers.add(Integer.parseInt(sb.toString()));
          sb.setLength(0);
        } else if (Character.isDigit(c)) {
          sb.append(c);
        } else if (!Character.isDigit(c)) {
          isNumberAdjacentToSymbol = false;
          sb.setLength(0);
        }

        if (j == line.size() - 1 && !sb.isEmpty()) {
          partNumbers.add(Integer.parseInt(sb.toString()));
        }


      }
    }
    return partNumbers.stream()
        .reduce(0, Integer::sum);
  }

  private boolean isNumberAdjacentToSymbol(int i, int j, List<List<Character>> engineSchematic) {
    List<Character> symbols = List.of('*', '#', '+', '$', '-');
    for (int k = -1; k <= 1; k++) {
      for (int l = -1; l <= 1; l++) {
        int nextI = i + k;
        if (nextI >= 0 && nextI < engineSchematic.size()) {
          List<Character> line = engineSchematic.get(nextI);
          int nextJ = j + l;
          if (nextJ >= 0 && nextJ < line.size()) {
            if (nextI == i && nextJ == j) {
              continue;
            }
            Character c = line.get(nextJ);
            if (symbols.contains(c)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}

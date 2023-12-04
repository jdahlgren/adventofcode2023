package se.johannesdahlgren.aoc23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day4 {

  private final String fileName;

  public Day4(String fileName) {
    this.fileName = fileName;
  }

  public int pointsOfWinningNumbers() {
    List<String> scratchCardTable = FileReader.readFileAsLines(fileName);
    return scratchCardTable.stream()
        .map(this::toScratchCards)
        .map(this::keepMyWinningNumbers)
        .filter(Predicate.not(List::isEmpty))
        .map(this::doubleNumbers)
        .reduce(0, Integer::sum);
  }

  private int doubleNumbers(List<Integer> myWinningNumbers) {
    int result = myWinningNumbers.stream()
        .mapToInt(myWinningNumber -> 2)
        .reduce(1, (a, b) -> a * b);
    return result / 2;
  }

  private ScratchCard toScratchCards(String line) {
    String[] split = line.substring(line.indexOf(":") + 1).split("\\|");
    List<Integer> winningNumbers = stringToNumberList(split[0]);
    List<Integer> myNumbersNumbers = stringToNumberList(split[1]);
    return new ScratchCard(winningNumbers, myNumbersNumbers);
  }

  private List<Integer> keepMyWinningNumbers(ScratchCard scratchCard) {
    ArrayList<Integer> myWinningNumbers = new ArrayList<>(scratchCard.myNumbers);
    boolean retained = myWinningNumbers.retainAll(scratchCard.winningNumbers);
    return retained ? myWinningNumbers : List.of();
  }

  private static List<Integer> stringToNumberList(String stringOfNumbers) {
    return Arrays.stream(stringOfNumbers.
            split(" "))
        .filter(Predicate.not(String::isEmpty))
        .map(Integer::parseInt)
        .toList();
  }

  private record ScratchCard(List<Integer> winningNumbers, List<Integer> myNumbers) {

  }
}

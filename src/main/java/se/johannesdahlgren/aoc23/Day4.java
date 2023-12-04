package se.johannesdahlgren.aoc23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        .map(ScratchCard::keepMyWinningNumbers)
        .filter(Predicate.not(List::isEmpty))
        .map(this::doubleNumbers)
        .reduce(0, Integer::sum);
  }

  public int numberOfScratchCards() {
    List<String> scratchCardTable = FileReader.readFileAsLines(fileName);
    List<ScratchCard> scratchCards = scratchCardTable.stream()
        .map(this::toScratchCards)
        .toList();

    ArrayList<ScratchCard> duplicatedScratchCards = new ArrayList<>(scratchCards);
    for (int i = 1; i <= duplicatedScratchCards.size(); i++) {
      ScratchCard scratchCard = duplicatedScratchCards.get(i - 1);
      if (scratchCard.isWinningCard()) {
        List<Integer> keepMyWinningNumbers = scratchCard.keepMyWinningNumbers();
        for (int j = 1; j <= keepMyWinningNumbers.size(); j++) {
          int nextCardNumber = scratchCard.cardNumber + j;
          int finalJ = j;
          Optional<ScratchCard> nextScratchCardOptional = scratchCards.stream()
              .filter(c -> c.cardNumber == scratchCard.cardNumber + finalJ)
              .findFirst();
          if (nextScratchCardOptional.isPresent()) {
            ScratchCard nextScratchCard = nextScratchCardOptional.get();
            duplicatedScratchCards.add(new ScratchCard(nextCardNumber, nextScratchCard.winningNumbers, nextScratchCard.myNumbers));
          }
        }
      }
    }

    return duplicatedScratchCards.size();
  }

  private int doubleNumbers(List<Integer> myWinningNumbers) {
    int result = myWinningNumbers.stream()
        .mapToInt(myWinningNumber -> 2)
        .reduce(1, (a, b) -> a * b);
    return result / 2;
  }

  private ScratchCard toScratchCards(String line) {
    int colonIndex = line.indexOf(":");
    int cardNumber = Integer.parseInt(line.substring(5, colonIndex).trim());
    String[] split = line.substring(colonIndex + 1).split("\\|");
    List<Integer> winningNumbers = stringToNumberList(split[0]);
    List<Integer> myNumbersNumbers = stringToNumberList(split[1]);
    return new ScratchCard(cardNumber, winningNumbers, myNumbersNumbers);
  }


  private static List<Integer> stringToNumberList(String stringOfNumbers) {
    return Arrays.stream(stringOfNumbers.
            split(" "))
        .filter(Predicate.not(String::isEmpty))
        .map(Integer::parseInt)
        .toList();
  }

  private record ScratchCard(int cardNumber, List<Integer> winningNumbers, List<Integer> myNumbers) {

    public List<Integer> keepMyWinningNumbers() {
      ArrayList<Integer> myWinningNumbers = new ArrayList<>(myNumbers);
      boolean retained = myWinningNumbers.retainAll(winningNumbers);
      return retained ? myWinningNumbers : List.of();
    }

    public boolean isWinningCard() {
      return !keepMyWinningNumbers().isEmpty();
    }
  }
}

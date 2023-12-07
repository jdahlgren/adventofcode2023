package se.johannesdahlgren.aoc23;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day7 {

  private final String fileName;
  private static final List<Character> cardValues = List.of('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', '1');

  public Day7(String fileName) {
    this.fileName = fileName;
  }

  public int calcTotalWinnings() {
    AtomicInteger index = new AtomicInteger(1);
    return FileReader.readFileAsLines(fileName).stream()
        .map(this::toHands)
        .sorted(Hand::compareTo)
        .map(h -> h.bet * index.getAndIncrement())
        .reduce(0, Integer::sum);
  }

  private Hand toHands(String line) {
    String cards = line.substring(0, 5);
    int bet = Integer.parseInt(line.substring(6));
    return new Hand(cards, bet);
  }

  private record Hand(String cards, int bet, HandStrength strength) {

    Hand(String cards, int bet) {
      this(cards, bet, HandStrength.from(cards));
    }


    public int compareTo(Hand h2) {
      int strength = h2.strength().compareTo(this.strength);
      if (strength != 0) {
        return strength;
      }
      for (int i = 0; i < cards.length(); i++) {
        int compareCard = Integer.compare(cardValues.indexOf(h2.cards.charAt(i)), cardValues.indexOf(cards.charAt(i)));
        if (compareCard != 0) {
          return compareCard;
        }
      }
      return 0;
    }

  }


  private enum HandStrength {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIRS,
    ONE_PAIR,
    HIGH_CARD;

    public static HandStrength from(String cards) {
      if (isFiveOfAKind(cards)) {
        return FIVE_OF_A_KIND;
      } else if (isXOfAKind(cards, 4)) {
        return FOUR_OF_A_KIND;
      } else if (isFullHouse(cards)) {
        return FULL_HOUSE;
      } else if (isXOfAKind(cards, 3)) {
        return THREE_OF_A_KIND;
      } else if (isTwoPairs(cards)) {
        return TWO_PAIRS;
      } else if (isXOfAKind(cards, 2)) {
        return ONE_PAIR;
      } else {
        return HIGH_CARD;
      }
    }

    private static boolean isFiveOfAKind(String cards) {
      return Pattern.compile("([A-Z])\\1{4}").matcher(cards).find();
    }

    private static boolean isXOfAKind(String cards, int numberOfSameCard) {
      return groupCards(cards)
          .values().stream()
          .anyMatch(l -> l == numberOfSameCard);
    }

    private static boolean isFullHouse(String cards) {
      if (isXOfAKind(cards, 3)) {
        Character threeOfAKind = getXOfAKind(cards, 3);
        String remainingCards = cards.replace(threeOfAKind.toString(), "");
        return isXOfAKind(remainingCards, 2);
      }
      return false;
    }

    private static boolean isTwoPairs(String cards) {
      if (isXOfAKind(cards, 2)) {
        Character twoOfAKind = getXOfAKind(cards, 2);
        String remainingCards = cards.replace(twoOfAKind.toString(), "");
        return isXOfAKind(remainingCards, 2);
      }
      return false;
    }

    private static Character getXOfAKind(String cards, int numberOfSameCard) {
      return groupCards(cards).entrySet().stream().filter(e -> e.getValue() == numberOfSameCard).findFirst().map(Entry::getKey).orElseThrow();
    }

    private static Map<Character, Long> groupCards(String cards) {
      return cards.chars()
          .mapToObj(e -> (char) e)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
  }
}

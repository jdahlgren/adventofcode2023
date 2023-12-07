package se.johannesdahlgren.aoc23;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import se.johannesdahlgren.aoc23.util.FileReader;

public class Day7 {

  private final String fileName;
  private static final char JOKER = 'J';
  private static final List<Character> cardValues = List.of('A', 'K', 'Q', JOKER, 'T', '9', '8', '7', '6', '5', '4', '3', '2');
  private static final List<Character> cardValuesJoker = List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', JOKER);

  public Day7(String fileName) {
    this.fileName = fileName;
  }

  public int calcTotalWinnings(boolean useJoker) {
    AtomicInteger index = new AtomicInteger(1);
    return FileReader.readFileAsLines(fileName).stream()
        .map(line -> toHands(line, useJoker))
        .sorted(Hand::compareTo)
        .map(h -> h.bet * index.getAndIncrement())
        .reduce(0, Integer::sum);
  }

  private Hand toHands(String line, boolean useJoker) {
    String cards = line.substring(0, 5);
    int bet = Integer.parseInt(line.substring(6));
    return new Hand(cards, bet, useJoker);
  }

  public record Hand(String cards, int bet, boolean useJoker, HandStrength strength) {

    Hand(String cards, int bet, boolean useJoker) {
      this(cards, bet, useJoker, HandStrength.from(cards, useJoker));
    }


    public int compareTo(Hand h2) {
      int strength = h2.strength().compareTo(this.strength);
      if (strength != 0) {
        return strength;
      }
      for (int i = 0; i < cards.length(); i++) {
        int compareCard = useJoker ?
            Integer.compare(cardValuesJoker.indexOf(h2.cards.charAt(i)), cardValuesJoker.indexOf(cards.charAt(i))) :
            Integer.compare(cardValues.indexOf(h2.cards.charAt(i)), cardValues.indexOf(cards.charAt(i)));
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

    public static HandStrength from(String cards, boolean useJoker) {
      if (isXOfAKind(cards, 5, useJoker)) {
        return FIVE_OF_A_KIND;
      } else if (isXOfAKind(cards, 4, useJoker)) {
        return FOUR_OF_A_KIND;
      } else if (isFullHouse(cards, useJoker)) {
        return FULL_HOUSE;
      } else if (isXOfAKind(cards, 3, useJoker)) {
        return THREE_OF_A_KIND;
      } else if (isTwoPairs(cards, useJoker, 2)) {
        return TWO_PAIRS;
      } else if (isXOfAKind(cards, 2, useJoker)) {
        return ONE_PAIR;
      } else {
        return HIGH_CARD;
      }
    }

    private static boolean isXOfAKind(String cards, int numberOfSameCard, boolean useJoker) {
      return groupCards(cards, useJoker)
          .values().stream()
          .anyMatch(l -> l == numberOfSameCard);
    }

    private static boolean isFullHouse(String cards, boolean useJoker) {
      return isTwoPairs(cards, useJoker, 3);
    }

    private static boolean isTwoPairs(String cards, boolean useJoker, int numberOfSameCard) {
      if (isXOfAKind(cards, numberOfSameCard, useJoker)) {
        Character twoOfAKind = getXOfAKind(cards, numberOfSameCard, useJoker);
        String remainingCards = cards.replace(twoOfAKind.toString(), "");
        if (useJoker) {
          remainingCards = remainingCards.replace(String.valueOf(JOKER), "");
        }
        return isXOfAKind(remainingCards, 2, useJoker);
      }
      return false;
    }

    private static Character getXOfAKind(String cards, int numberOfSameCard, boolean useJoker) {
      return groupCards(cards, useJoker).entrySet().stream().filter(e -> e.getValue() == numberOfSameCard).findFirst().map(Entry::getKey)
          .orElseThrow();
    }

    private static Map<Character, Long> groupCards(String cards, boolean useJoker) {
      Map<Character, Long> cardGroup = cards.chars()
          .mapToObj(e -> (char) e)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      if (!useJoker || !cardGroup.containsKey(JOKER) || cardGroup.get(JOKER) == 5) {
        return cardGroup;
      } else {
        Long numberOfJokers = cardGroup.get(JOKER);
        cardGroup.remove(JOKER);
        Character bestNonJoker = cardGroup.entrySet().stream().max(Entry.comparingByValue()).map(Entry::getKey).orElseThrow();
        return cardGroup.entrySet().stream()
            .collect(Collectors.toMap(Entry::getKey, e -> e.getKey().equals(bestNonJoker) ? e.getValue() + numberOfJokers : e.getValue()));
      }
    }
  }
}

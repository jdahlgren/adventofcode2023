package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day4Test {

  @Test
  void part1Example() {
    Day4 day4 = new Day4("day4example.txt");
    int points = day4.pointsOfWinningNumbers();
    assertThat(points, is(13));
  }

  @Test
  void part1() {
    Day4 day4 = new Day4("day4.txt");
    int points = day4.pointsOfWinningNumbers();
    assertThat(points, is(23235));
  }


  @Test
  void part2Example() {
    Day4 day4 = new Day4("day4example.txt");
    int numberOfScratchCards = day4.numberOfScratchCards();
    assertThat(numberOfScratchCards, is(30));
  }

  @Test
  void part2() {
    Day4 day4 = new Day4("day4.txt");
    int numberOfScratchCards = day4.numberOfScratchCards();
    assertThat(numberOfScratchCards, is(5920640));
  }
}
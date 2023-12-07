package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day7Test {

  @Test
  void part1Example() {
    Day7 day7 = new Day7("day7example.txt");
    int totalWinnings = day7.calcTotalWinnings();
    assertThat(totalWinnings, is(6440));
  }

  @Test
  void part1() {
    Day7 day7 = new Day7("day7.txt");
    int totalWinnings = day7.calcTotalWinnings();
    assertThat(totalWinnings, is(246424613));
  }
}
package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day7Test {

  @Test
  void part1Example() {
    Day7 day7 = new Day7("day7example.txt");
    int totalWinnings = day7.calcTotalWinnings(false);
    assertThat(totalWinnings, is(6440));
  }

  @Test
  void part1Example2() {
    Day7 day7 = new Day7("day7example2.txt");
    int totalWinnings = day7.calcTotalWinnings(false);
    assertThat(totalWinnings, is(6592));
  }

  @Test
  void part1() {
    Day7 day7 = new Day7("day7.txt");
    int totalWinnings = day7.calcTotalWinnings(false);
    assertThat(totalWinnings, is(246424613));
  }

  @Test
  void part2Example() {
    Day7 day7 = new Day7("day7example.txt");
    int totalWinnings = day7.calcTotalWinnings(true);
    assertThat(totalWinnings, is(5905));
  }

  @Test
  void part2Example2() {
    Day7 day7 = new Day7("day7example2.txt");
    int totalWinnings = day7.calcTotalWinnings(true);
    assertThat(totalWinnings, is(6839));
  }

  @Test
  void part2() {
    Day7 day7 = new Day7("day7.txt");
    int totalWinnings = day7.calcTotalWinnings(true);
    assertThat(totalWinnings, is(248256639));
  }
}
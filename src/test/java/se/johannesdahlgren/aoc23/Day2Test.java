package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day2Test {

  @Test
  void part1Example() {
    Day2 day2 = new Day2("day2example.txt");
    int sum = day2.sumOfPossibleGameId(12, 13, 14);
    assertThat(sum, is(8));
  }

  @Test
  void part1() {
    Day2 day2 = new Day2("day2.txt");
    int sum = day2.sumOfPossibleGameId(12, 13, 14);
    assertThat(sum, is(2449));
  }

}
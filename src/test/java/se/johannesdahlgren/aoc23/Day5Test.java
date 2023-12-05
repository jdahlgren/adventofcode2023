package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day5Test {

  @Test
  void part1Example() {
    Day5 day5 = new Day5("day5example.txt");
    long temp = day5.getLowestLocationNumber();
    assertThat(temp, is(35L));
  }

  @Test
  void part1() {
    Day5 day5 = new Day5("day5.txt");
    long temp = day5.getLowestLocationNumber();
    assertThat(temp, is(486613012L));
  }

  @Test
  void part2Example() {
    Day5 day5 = new Day5("day5example.txt");
    long temp = day5.getLowestLocationNumberRange();
    assertThat(temp, is(46L));
  }

  @Test
  void part2() {
    Day5 day5 = new Day5("day5.txt");
    long temp = day5.getLowestLocationNumberRange();
    assertThat(temp, is(56931769L));
  }
}
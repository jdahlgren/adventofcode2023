package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day8Test {

  @Test
  void part1Example() {
    Day8 day8 = new Day8("day8example.txt");
    int numberOfStepsToEnd = day8.numberOfStepsToEnd();
    assertThat(numberOfStepsToEnd, is(2));
  }

  @Test
  void part1() {
    Day8 day8 = new Day8("day8.txt");
    int numberOfStepsToEnd = day8.numberOfStepsToEnd();
    assertThat(numberOfStepsToEnd, is(16343));
  }

}
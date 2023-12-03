package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day3Test {

  @Test
  void part1Example() {
    Day3 day3 = new Day3("day3example.txt");
    int sumOfPartNumbers = day3.sumOfPartNumbers();
    assertThat(sumOfPartNumbers, is(4361));
  }

  @Test
  void part1Example2() {
    Day3 day3 = new Day3("day3example2.txt");
    int sumOfPartNumbers = day3.sumOfPartNumbers();
    assertThat(sumOfPartNumbers, is(40));
  }

  @Test
  void part1Example3() {
    Day3 day3 = new Day3("day3example3.txt");
    int sumOfPartNumbers = day3.sumOfPartNumbers();
    assertThat(sumOfPartNumbers, is(413));
  }

  @Test
  void part1Example4() {
    Day3 day3 = new Day3("day3example4.txt");
    int sumOfPartNumbers = day3.sumOfPartNumbers();
    assertThat(sumOfPartNumbers, is(925));
  }

  @Test
  void part1() {
    Day3 day3 = new Day3("day3.txt");
    int sumOfPartNumbers = day3.sumOfPartNumbers();
    assertThat(sumOfPartNumbers, is(0)); //WTF?
  }

}

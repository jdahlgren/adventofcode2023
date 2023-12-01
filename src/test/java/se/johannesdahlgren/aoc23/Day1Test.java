package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day1Test {

  @Test
  void day1Part1Example() {
    Day1 day1 = new Day1();
    int sumOfCalibrationValues = day1.sumOfCalibrationValues("day1example.txt");
    assertThat(sumOfCalibrationValues, is(142));
  }

  @Test
  void day1Part1() {
    Day1 day1 = new Day1();
    int sumOfCalibrationValues = day1.sumOfCalibrationValues("day1.txt");
    assertThat(sumOfCalibrationValues, is(55208));
  }
}
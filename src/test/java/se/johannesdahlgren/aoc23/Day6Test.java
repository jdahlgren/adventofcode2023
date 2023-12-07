package se.johannesdahlgren.aoc23;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.johannesdahlgren.aoc23.Day6.Race;

class Day6Test {

  @Test
  void part1Example() {
    List<Race> races = List.of(
        new Race(7, 9),
        new Race(15, 40),
        new Race(30, 200));
    Day6 day6 = new Day6(races);
    int productOfWaysToBeatRecord = day6.getWaysToBeatRecord().stream()
        .reduce(1, (a, b) -> a * b);

    assertThat(productOfWaysToBeatRecord, is(288));
  }

  @Test
  void part1() {
    List<Race> races = List.of(
        new Race(40, 219),
        new Race(81, 1012),
        new Race(77, 1365),
        new Race(72, 1089));
    Day6 day6 = new Day6(races);
    int productOfWaysToBeatRecord = day6.getWaysToBeatRecord().stream()
        .reduce(1, (a, b) -> a * b);

    assertThat(productOfWaysToBeatRecord, is(861300));
  }

}
package se.johannesdahlgren.aoc23;

import java.util.List;

public class Day6 {

  private final List<Race> races;

  public Day6(List<Race> races) {

    this.races = races;
  }

  public List<Integer> getWaysToBeatRecord() {
    return races.stream()
        .map(this::getNumberOfWaysToBeatRecord)
        .toList();
  }

  private int getNumberOfWaysToBeatRecord(Race race) {
    int speed = 0;
    int duration = race.length;
    int numberOfRecords = 0;
    for (int i = 0; i < race.length; i++) {
      speed++;
      duration--;
      if (speed * duration > race.record) {
        numberOfRecords++;
      }
    }
    return numberOfRecords;
  }

  public record Race(int length, int record) {

  }
}

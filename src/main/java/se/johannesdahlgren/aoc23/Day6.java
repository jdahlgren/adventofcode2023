package se.johannesdahlgren.aoc23;

import java.util.List;

public class Day6 {

  private final List<Race> races;

  public Day6(List<Race> races) {

    this.races = races;
  }

  public List<Long> getWaysToBeatRecord() {
    return races.stream()
        .map(this::getNumberOfWaysToBeatRecord)
        .toList();
  }

  private long getNumberOfWaysToBeatRecord(Race race) {
    long speed = 0;
    long duration = race.length;
    long numberOfRecords = 0;
    for (long i = 0; i < race.length; i++) {
      speed++;
      duration--;
      if (speed * duration > race.record) {
        numberOfRecords++;
      }
    }
    return numberOfRecords;
  }

  public record Race(long length, long record) {

  }
}

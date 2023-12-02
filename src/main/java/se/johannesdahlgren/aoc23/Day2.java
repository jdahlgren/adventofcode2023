package se.johannesdahlgren.aoc23;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import se.johannesdahlgren.aoc23.util.FileReader;

public final class Day2 {

  private final String fileName;

  public Day2(String fileName) {
    this.fileName = fileName;
  }

  public int sumOfPossibleGameId(int numberOfRed, int numberOfGreen, int numberOfBlue) {
    List<String> gameLines = FileReader.readFileAsLines(fileName);
    return gameLines.stream()
        .map(this::toGame)
        .filter(game -> !isImpossible(game, numberOfRed, numberOfGreen, numberOfBlue))
        .map(Game::gameId)
        .reduce(0, Integer::sum);
  }

  public int sumOfPowerOfMinNumberOfCubes() {
    List<String> gameLines = FileReader.readFileAsLines(fileName);
    return gameLines.stream()
        .map(this::toGame)
        .map(this::powerOfMinNumberOfCubes)
        .reduce(0, Integer::sum);
  }

  private Game toGame(String gameLine) {
    int gameId = getGameId(gameLine);
    List<CubeSet> cubeSets = getCubeSets(gameLine);
    return new Game(gameId, cubeSets);
  }

  private boolean isImpossible(Game game, int numberOfRed, int numberOfGreen, int numberOfBlue) {
    return game.cubeSets.stream()
        .anyMatch(cubeSet -> cubeSet.numberOfRed > numberOfRed
            || cubeSet.numberOfGreen > numberOfGreen
            || cubeSet.numberOfBlue > numberOfBlue);
  }

  private int getGameId(String gameLine) {
    Matcher matcher = Pattern.compile("Game (\\d+):").matcher(gameLine);
    if (!matcher.find()) {
      return 0;
    }
    return Integer.parseInt(matcher.group(1));
  }

  private List<CubeSet> getCubeSets(String gameLine) {
    String setLine = gameLine.substring(gameLine.indexOf(":") + 1);
    return Arrays.stream(setLine.split(";"))
        .map(this::getCubeSet)
        .toList();
  }

  private CubeSet getCubeSet(String set) {
    String[] split = set.split(",");
    int red = 0;
    int green = 0;
    int blue = 0;
    for (String cube : split) {
      Matcher matcher = Pattern.compile("(\\d+) (\\w+)").matcher(cube);
      if (!matcher.find()) {
        return new CubeSet(0, 0, 0);
      }
      switch (matcher.group(2)) {
        case "red" -> red = Integer.parseInt(matcher.group(1));
        case "green" -> green = Integer.parseInt(matcher.group(1));
        case "blue" -> blue = Integer.parseInt(matcher.group(1));
      }
    }
    return new CubeSet(red, green, blue);
  }

  private int powerOfMinNumberOfCubes(Game game) {
    int maxRed = Integer.MIN_VALUE;
    int maxGreen = Integer.MIN_VALUE;
    int maxBlue = Integer.MIN_VALUE;
    for (CubeSet cubeSet : game.cubeSets()) {
      maxRed = Math.max(cubeSet.numberOfRed, maxRed);
      maxGreen = Math.max(cubeSet.numberOfGreen, maxGreen);
      maxBlue = Math.max(cubeSet.numberOfBlue, maxBlue);
    }
    return maxRed * maxGreen * maxBlue;
  }

  record Game(int gameId, List<CubeSet> cubeSets) {

  }

  record CubeSet(int numberOfRed, int numberOfGreen, int numberOfBlue) {

  }
}

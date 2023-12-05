package se.johannesdahlgren.aoc23.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

  public static List<String> readFileAsLines(String fileName) {
    Path path = getPath(fileName);
    try {
      return Files.readAllLines(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<List<Character>> readFileAsCharMatrix(String fileName) {
    return readFileAsLines(fileName).stream()
        .map(line -> line.chars()
            .mapToObj(c -> (char) c)
            .toList())
        .toList();

  }

  public static String readFileToString(String fileName) {
    Path path = getPath(fileName);
    try {
      return Files.readString(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Path getPath(String fileName) {
    return Paths.get("src/main/resources/" + fileName);
  }
}

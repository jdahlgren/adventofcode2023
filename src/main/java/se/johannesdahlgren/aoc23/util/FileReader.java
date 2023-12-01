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

  private static Path getPath(String fileName) {
    return Paths.get("src/main/resources/" + fileName);
  }
}

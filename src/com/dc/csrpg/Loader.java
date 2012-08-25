package com.dc.csrpg;

import java.util.List;

public class Loader {
  public static Grid<Tile> loadGridFromFile(String filename) {
    List<String> lines = Util.readFile(filename);
    if (lines.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int worldWidth = lines.get(0).length();
    int worldHeight = lines.size();
    if (worldHeight % Constants.SCREEN_HEIGHT_TILES != 0) {
      throw new IllegalArgumentException("height of " + worldHeight + " should be divisible by " + Constants.SCREEN_HEIGHT_TILES);
    }

    Grid<Tile> grid = new Grid<Tile>(worldWidth, worldHeight) {
      @Override
      protected Tile createDefault(int x, int y) {
        return new Tile(Tile.Type.GRASS, true);
      }
    };

    for (int y = 0; y < lines.size(); y++) {
      String line = lines.get(y);
      if (worldWidth % Constants.SCREEN_WIDTH_TILES != 0) {
        throw new IllegalArgumentException("width of " + worldWidth + " should be divisible by " + Constants.SCREEN_WIDTH_TILES);
      }
      for (int x = 0; x < line.length(); x++) {
        grid.set(x, y, charToTile(line.charAt(x)));
      }
    }
    return grid;
  }

  private static Tile charToTile(char c) {
    switch (c) {
      case '.': return new Tile(Tile.Type.GRASS, true);
      case 'w': return new Tile(Tile.Type.WALL, false);
      default: throw new IllegalArgumentException();
    }
  }
}

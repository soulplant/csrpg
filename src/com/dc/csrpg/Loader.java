package com.dc.csrpg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Loader {
  private static class EntityDescriptor {
    public enum Type {
      TILE
    }
    public Type type;
    public Tile.Type tileType;
    public boolean passable;

    public Tile toTile() {
      if (type != Type.TILE) {
        throw new IllegalStateException(type + " != TILE");
      }
      return new Tile(tileType, passable);
    }
  }

  public static Grid<Tile> loadGridFromFile(String filename) {
    List<String> lines = Util.readFile(filename);
    if (lines.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int worldWidth = lines.get(0).length();
    int emptyLineIndex = lines.indexOf("");
    int worldHeight = emptyLineIndex < 0 ? lines.size() : emptyLineIndex;

    if (worldHeight % Constants.SCREEN_HEIGHT_TILES != 0) {
      throw new IllegalArgumentException("height of " + worldHeight + " should be divisible by " + Constants.SCREEN_HEIGHT_TILES);
    }

    Grid<Tile> grid = new Grid<Tile>(worldWidth, worldHeight) {
      @Override
      protected Tile createDefault(int x, int y) {
        return new Tile(Tile.Type.GRASS, true);
      }
    };

    Map<Character, EntityDescriptor> entityMap =
        new HashMap<Character, EntityDescriptor>();

    // Parse the entities.
    for (int y = worldHeight + 1; y < lines.size(); y++) {
      String line = lines.get(y);
      char entityChar = line.charAt(0);
      Gson gson = new Gson();
      EntityDescriptor entityDescriptor = gson.fromJson(line.substring(1),
          EntityDescriptor.class);
      entityMap.put(entityChar, entityDescriptor);
    }

    // Parse the entity map.
    for (int y = 0; y < worldHeight; y++) {
      String line = lines.get(y);
      if (worldWidth % Constants.SCREEN_WIDTH_TILES != 0) {
        throw new IllegalArgumentException("width of " + worldWidth + " should be divisible by " + Constants.SCREEN_WIDTH_TILES);
      }
      for (int x = 0; x < line.length(); x++) {
        grid.set(x, y, makeTile(entityMap.get(line.charAt(x))));
      }
    }
    return grid;
  }

  private static Tile makeTile(EntityDescriptor entityDescriptor) {
    if (entityDescriptor.type != EntityDescriptor.Type.TILE) {
      return new Tile(Tile.Type.GRASS, true);
    }
    return entityDescriptor.toTile();
  }
}

package com.dc.csrpg;

import com.dc.csrpg.Tile.Type;

public class TileGrid {
  private final Tile[][] tiles;
  private final int width;
  private final int height;

  public TileGrid(int width, int height) {
    this.width = width;
    this.height = height;

    tiles = new Tile[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        tiles[x][y] = new Tile(Type.GRASS, true);
      }
    }
  }

  public Tile get(int x, int y) {
    return tiles[x][y];
  }

  public Tile get(Point point) {
    return get(point.x, point.y);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void set(int x, int y, Tile tile) {
    tiles[x][y] = tile;
  }

  public void set(Point point, Tile tile) {
    set(point.x, point.y, tile);
  }
}

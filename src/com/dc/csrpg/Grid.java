package com.dc.csrpg;

import java.util.ArrayList;
import java.util.List;


public abstract class Grid<T> {
  private final List<T> tiles = new ArrayList<T>();
  private final int width;
  private final int height;

  public Grid(int width, int height) {
    this.width = width;
    this.height = height;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        tiles.add(createDefault(x, y));
      }
    }
  }

  abstract protected T createDefault(int x, int y);

  public T get(int x, int y) {
    return tiles.get(getOffsetIndex(x, y));
  }

  private int getOffsetIndex(int x, int y) {
    return y * width + x;
  }

  public T get(Point point) {
    return get(point.x, point.y);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void set(int x, int y, T tile) {
    tiles.set(getOffsetIndex(x, y), tile);
  }

  public void set(Point point, T tile) {
    set(point.x, point.y, tile);
  }
}

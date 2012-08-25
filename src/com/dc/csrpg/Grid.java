package com.dc.csrpg;

import java.util.ArrayList;
import java.util.List;


public class Grid<T> {
  public class Subview {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Subview(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    public T get(Point point) {
      return Grid.this.get(getTranslatedPoint(point));
    }

    public T get(int x, int y) {
      return get(new Point(x, y));
    }

    private Point getTranslatedPoint(Point point) {
      return new Point(this.x + point.x, this.y + point.y);
    }

    public void set(Point point, T t) {
      Grid.this.set(getTranslatedPoint(point), t);
    }

    public void set(int x, int y, T t) {
      set(new Point(x, y), t);
    }

    public int getWidth() {
      return width;
    }

    public int getHeight() {
      return height;
    }
  }

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

  protected T createDefault(int x, int y) {
    return null;
  }

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

  /**
   * @param point in subview space
   * @param width of subview
   * @param height of subview
   * @return
   */
  public Subview getSubview(Point point, int width, int height) {
    return new Subview(point.x * width, point.y * height, width, height);
  }
}

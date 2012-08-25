package com.dc.csrpg;

public enum Direction {
  UP, DOWN, LEFT, RIGHT;

  public Point getOffset() {
    switch (this) {
    case UP: return new Point(0, -1);
    case DOWN: return new Point(0, 1);
    case LEFT: return new Point(-1, 0);
    case RIGHT: return new Point(1, 0);
    default: throw new IllegalStateException();
    }
  }
}

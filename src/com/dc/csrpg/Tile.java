package com.dc.csrpg;

public class Tile {
  public enum Type {
    GRASS, WALL
  }

  public final boolean passable;
  public final Type type;

  public Tile(Type type, boolean passable) {
    this.type = type;
    this.passable = passable;
  }
}

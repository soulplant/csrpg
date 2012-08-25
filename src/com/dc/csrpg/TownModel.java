package com.dc.csrpg;


public class TownModel {
  private Point playerPosition = new Point(0, 0);
  private Point viewportPosition = new Point(0, 0);
  private final Grid<Tile> tiles;

  public TownModel(Grid<Tile> tiles) {
    this.tiles = tiles;
  }

  private Grid<Tile>.Subview getSubview(int x, int y) {
    return tiles.getSubview(new Point(x, y), Constants.SCREEN_WIDTH_TILES,
        Constants.SCREEN_HEIGHT_TILES);
  }

  private Grid<Tile>.Subview getSubview(Point point) {
    return getSubview(point.x, point.y);
  }

  public Point getPlayerPosition() {
    return playerPosition;
  }

  public void setPlayerPosition(Point playerPosition) {
    this.playerPosition = playerPosition;
    viewportPosition = calculateTopLevelGrid(playerPosition);
  }

  private Point calculateTopLevelGrid(Point point) {
    int x = point.x / Constants.SCREEN_WIDTH_TILES;
    int y = point.y / Constants.SCREEN_HEIGHT_TILES;
    return new Point(x, y);
  }

  private Point calculateOffsetWithinSubview(Point point) {
    int x = point.x % Constants.SCREEN_WIDTH_TILES;
    int y = point.y % Constants.SCREEN_HEIGHT_TILES;
    return new Point(x, y);
  }

  public Point getViewportPosition() {
    return viewportPosition;
  }

  public Tile getTile(Point point) {
    return tiles.get(point);
  }

  public Grid<Tile>.Subview getViewportTiles() {
    return getSubview(viewportPosition);
  }

  public boolean isInWorldBounds(Point point) {
    return point.x >= 0 && point.x < getWorldWidth() &&
        point.y >= 0 && point.y < getWorldHeight();
  }

  public int getWorldWidth() {
    return tiles.getWidth();
  }

  public int getWorldHeight() {
    return tiles.getHeight();
  }

  public Point getPlayerViewportPosition() {
    return calculateOffsetWithinSubview(playerPosition);
  }

  public boolean isPassable(Point point) {
    Tile tile = getTile(point);
    return tile.passable && !tile.hasNPC() && !tile.hasItem();
  }
}

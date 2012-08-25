package com.dc.csrpg;


public class TownModel {
  private Point playerPosition = new Point(0, 0);
  private Point viewportPosition = new Point(0, 0);

  private final Grid<Tile> tiles = new Grid<Tile>(
      Constants.WORLD_WIDTH * Constants.SCREEN_WIDTH_TILES,
      Constants.WORLD_HEIGHT * Constants.SCREEN_HEIGHT_TILES) {
    @Override
    protected Tile createDefault(int x, int y) {
      return new Tile(Tile.Type.GRASS, true);
    }
  };


  public TownModel() {
    getSubview(0, 0).set(5, 0, new Tile(Tile.Type.WALL, false));
    getSubview(1, 0).set(5, 5, new Tile(Tile.Type.WALL, false));
    getSubview(1, 1).set(0, 5, new Tile(Tile.Type.WALL, false));
    getSubview(0, 1).set(10, 3, new Tile(Tile.Type.WALL, false));
  }

  private Grid<Tile>.Subview getSubview(int x, int y) {
    return tiles.getSubview(new Point(x, y), Constants.SCREEN_WIDTH_TILES, Constants.SCREEN_HEIGHT_TILES);
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

//  public Grid<Tile> getTiles() {
//    return tiles;
//  }

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
}

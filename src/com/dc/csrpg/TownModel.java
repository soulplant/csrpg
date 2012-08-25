package com.dc.csrpg;

public class TownModel {
  private Point playerPosition = new Point(0, 0);
  private Point viewportPosition = new Point(0, 0);

  private final Grid<Grid<Tile>> tiles = new Grid<Grid<Tile>>(
      Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT) {
    @Override
    protected Grid<Tile> createDefault(int x, int y) {
      return new Grid<Tile>(
          Constants.SCREEN_WIDTH_TILES, Constants.SCREEN_HEIGHT_TILES) {
        @Override
        protected Tile createDefault(int x, int y) {
          return new Tile(Tile.Type.GRASS, true);
        }
      };
    }
  };


  public TownModel() {
    tiles.get(0, 0).set(5, 0, new Tile(Tile.Type.WALL, false));
    tiles.get(1, 0).set(5, 5, new Tile(Tile.Type.WALL, false));
    tiles.get(1, 1).set(0, 5, new Tile(Tile.Type.WALL, false));
    tiles.get(0, 1).set(10, 3, new Tile(Tile.Type.WALL, false));
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

  private Point calculateOffsetWithinGrid(Point point) {
    int x = point.x % Constants.SCREEN_WIDTH_TILES;
    int y = point.y % Constants.SCREEN_HEIGHT_TILES;
    return new Point(x, y);
  }

  public Grid<Grid<Tile>> getTiles() {
    return tiles;
  }

  public Point getViewportPosition() {
    return viewportPosition;
  }

  public Tile getTile(Point point) {
    Point viewport = calculateTopLevelGrid(point);
    Point inside = calculateOffsetWithinGrid(point);
    return tiles.get(viewport).get(inside);
  }

  public Grid<Tile> getViewportTiles() {
    return tiles.get(viewportPosition);
  }

  public boolean isInWorldBounds(Point point) {
    return point.x >= 0 && point.x < getWorldWidth() &&
        point.y >= 0 && point.y < getWorldHeight();
  }

  public int getWorldWidth() {
    return tiles.getWidth() * tiles.get(new Point(0, 0)).getWidth();
  }

  public int getWorldHeight() {
    return tiles.getHeight() * tiles.get(new Point(0, 0)).getHeight();
  }

  public Point getPlayerViewportPosition() {
    return calculateOffsetWithinGrid(playerPosition);
  }
}

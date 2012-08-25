package com.dc.csrpg;

public class TownModel {
  private Point playerPosition = new Point(0, 0);

  private final Grid<Tile> tiles = new Grid<Tile>(
      Constants.SCREEN_WIDTH_PX / Constants.TILE_WIDTH_PX,
      Constants.SCREEN_HEIGHT_PX / Constants.TILE_HEIGHT_PX) {

    @Override
    protected Tile createDefault(int x, int y) {
      return new Tile(Tile.Type.GRASS, true);
    }
  };

  public TownModel() {
    tiles.set(5, 0, new Tile(Tile.Type.WALL, false));
  }

  public Point getPlayerPosition() {
    return playerPosition;
  }

  public void setPlayerPosition(Point playerPosition) {
    this.playerPosition = playerPosition;
  }

  public Grid<Tile> getTiles() {
    return tiles;
  }
}

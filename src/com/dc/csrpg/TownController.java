package com.dc.csrpg;

public class TownController {
  private final TownModel model;

  public TownController(TownModel model) {
    this.model = model;
  }

  public void onPlayerMove(Direction direction) {
    Point newPlayerPosition = model.getPlayerPosition().move(direction);
    if (!isInBounds(newPlayerPosition)) {
      return;
    }
    if (model.getTiles().get(newPlayerPosition).passable) {
      model.setPlayerPosition(newPlayerPosition);
    }
  }

  private boolean isInBounds(Point point) {
    return point.x >= 0 && point.x < model.getTiles().getWidth() &&
        point.y >= 0 && point.y < model.getTiles().getHeight();
  }
}

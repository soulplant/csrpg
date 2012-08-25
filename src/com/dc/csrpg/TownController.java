package com.dc.csrpg;

public class TownController {
  private final TownModel model;

  public TownController(TownModel model) {
    this.model = model;
  }

  public void onPlayerMove(Direction direction) {
    Point newPlayerPosition = model.getPlayerPosition().move(direction);
    if (!model.isInWorldBounds(newPlayerPosition)) {
      return;
    }
    if (model.getTile(newPlayerPosition).passable) {
      model.setPlayerPosition(newPlayerPosition);
    }
  }
}

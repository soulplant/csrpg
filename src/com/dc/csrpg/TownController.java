package com.dc.csrpg;

public class TownController {
  private final TownModel model;

  public TownController(TownModel model) {
    this.model = model;
  }

  public void onPlayerMove(Direction direction) {
    Point newPlayerPosition = model.getPlayerPosition().move(direction);
    model.setPlayerPosition(newPlayerPosition);
  }
}

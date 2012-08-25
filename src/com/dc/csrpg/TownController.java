package com.dc.csrpg;

public class TownController {
  private final TownModel model;

  public TownController(TownModel model) {
    this.model = model;
  }

  public void onPlayerMove(Direction direction) {
    if (model.hasAlert()) {
      model.clearAlert();
      return;
    }

    Point newPlayerPosition = model.getPlayerPosition().move(direction);
    if (!model.isInWorldBounds(newPlayerPosition)) {
      return;
    }
    if (model.isPassable(newPlayerPosition)) {
      model.setPlayerPosition(newPlayerPosition);
    }
    Tile tile = model.getTile(newPlayerPosition);
    if (tile.hasNPC()) {
      model.setAlert(tile.getNPC().getMessage());
    }
    if (tile.hasItem()) {
      model.setAlert("You got: " + tile.getItem().getName() + "!");
      model.removeItem(newPlayerPosition);
    }
  }
}

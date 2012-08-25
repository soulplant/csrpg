package com.dc.csrpg.model.battle;

public class Battle {

  private final Entity player;
  private final Entity enemy;

  private boolean playerTurn;

  public Battle(Entity player, Entity enemy, boolean playerTurn) {
    super();
    this.player = player;
    this.enemy = enemy;
    this.setPlayerTurn(playerTurn);
  }

  public Entity getPlayer() {
    return player;
  }

  public Entity getEnemy() {
    return enemy;
  }

  public boolean isPlayerTurn() {
    return playerTurn;
  }

  public void setPlayerTurn(boolean playerTurn) {
    this.playerTurn = playerTurn;
  }

  public void alternateTurn() {
    playerTurn = !playerTurn;
  }

  public Entity getCurrentEntity() {
    if (playerTurn) {
      return player;
    } else {
      return enemy;
    }
  }

}

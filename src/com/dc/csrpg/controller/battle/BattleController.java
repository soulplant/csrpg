package com.dc.csrpg.controller.battle;

import com.dc.csrpg.model.battle.Battle;
import com.dc.csrpg.model.battle.Skill;
import com.dc.csrpg.view.battle.BattleView;

public class BattleController {

  private final Battle battle;

  private BattleView view;

  public BattleController(Battle battle) {
    this.battle = battle;
  }

  public void setView(BattleView view) {
    this.view = view;
    refresh();
  }

  public void refresh() {
    view.setPlayerName(battle.getPlayer().getName());
    view.setEnemyName(battle.getEnemy().getName());
    view.updatePlayerHp(battle.getPlayer().getCurrentHp(), battle.getPlayer().getMaxHp());
    view.updateEnemyHp(battle.getEnemy().getCurrentHp(), battle.getEnemy().getMaxHp());
  }

  public void onPlayerAttack() {
    assert battle.isPlayerTurn();

    battle.getEnemy().takeHit(battle.getPlayer().getAttackDamage());
//    battle.alternateTurn();

    refresh();
  }

  public void onPlayerSkill(Skill skill) {

  }

}

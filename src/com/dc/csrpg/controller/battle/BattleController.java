package com.dc.csrpg.controller.battle;

import java.util.ArrayList;
import java.util.List;

import com.dc.csrpg.model.battle.Battle;
import com.dc.csrpg.model.battle.Skill;
import com.dc.csrpg.view.battle.BattleView;

public class BattleController {
  private static final List<String> MAIN_ITEMS = new ArrayList<String>();
  static {
    MAIN_ITEMS.add("Attack");
    MAIN_ITEMS.add("Skills");
  };
  
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
    view.showSkills(battle.getPlayer().getSkills());
    view.showMenu(MAIN_ITEMS);
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

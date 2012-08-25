package com.dc.csrpg.view.battle;

import com.dc.csrpg.model.battle.Skill;

import java.util.List;

public interface BattleView {

  void showMenu(List<String> items);
  void showSkills(List<Skill> skills);
  void updatePlayerHp(int currentHp, int maxHp);
  void updateEnemyHp(int currentHp, int maxHp);
  void refresh();
  void setPlayerName(String playerName);
  void setEnemyName(String enemyName);
}

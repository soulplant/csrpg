package com.dc.csrpg.view.battle;

import java.util.List;

public interface BattleView {

  void refresh();

  void showMenu(List<String> items);
  void setSelectedIndex(int selectedIndex);

  void setPlayerName(String playerName);
  void setEnemyName(String enemyName);

  void updatePlayerHp(int currentHp, int maxHp);
  void updateEnemyHp(int currentHp, int maxHp);

}

package com.dc.csrpg.controller.battle;

import com.dc.csrpg.model.battle.Battle;
import com.dc.csrpg.model.battle.MenuState;
import com.dc.csrpg.model.battle.MenuState.Menu;
import com.dc.csrpg.model.battle.Skill;
import com.dc.csrpg.view.battle.BattleView;

import java.util.ArrayList;
import java.util.List;

public class BattleController {
  private static final List<String> MAIN_ITEMS = new ArrayList<String>();
  static {
    MAIN_ITEMS.add("Attack");
    MAIN_ITEMS.add("Skills");
  };

  private final MenuState menu;
  private final Battle battle;

  private BattleView view;

  public BattleController(Battle battle, MenuState menu) {
    this.battle = battle;
    this.menu = menu;
  }

  public void setView(BattleView view) {
    this.view = view;
    refresh();
  }

  public void refresh() {
    switch (menu.getCurrentMenu()) {
    case MAIN:
      view.showMenu(MAIN_ITEMS);
      break;
    case SKILLS:
      view.showMenu(toStrings(battle.getCurrentEntity().getSkills()));
      break;
    default:
      throw new IllegalStateException();
    }

    view.setSelectedIndex(menu.getSelectedIndex());

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

  public void onMenuUp() {
    menu.maybeMoveUp(currentMenuSize(menu.getCurrentMenu()));
    refresh();
  }

  public void onMenuDown() {
    menu.maybeMoveDown(currentMenuSize(menu.getCurrentMenu()));
    refresh();
  }

  public void onMenuAction() {
    switch (menu.getCurrentMenu()) {
    case MAIN:
      switch (menu.getSelectedIndex()) {
      case 0: // Attack
        onPlayerAttack();
        break;
      case 1: // Skillz0rs
        menu.setCurrentMenu(Menu.SKILLS);
        break;
      default:
        throw new IllegalStateException();
      }
      break;
    case SKILLS:
      onPlayerSkill(battle.getCurrentEntity().getSkills().get(menu.getSelectedIndex()));
      break;
    default:
      throw new IllegalStateException();
    }
    refresh();
  }

  public int currentMenuSize(Menu menuType) {
    switch (menuType) {
    case MAIN:
      return MAIN_ITEMS.size();
    case SKILLS:
      return battle.getCurrentEntity().getSkills().size();
    default:
      throw new IllegalStateException();
    }
  }

  private static List<String> toStrings(List<Skill> skills) {
    List<String> strings = new ArrayList<String>();
    for (Skill skill : skills) {
      strings.add(skill.getName());
    }
    return strings;
  }

}

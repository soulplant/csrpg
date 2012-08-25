package com.dc.csrpg;

import com.dc.csrpg.controller.battle.BattleController;
import com.dc.csrpg.model.battle.Battle;
import com.dc.csrpg.model.battle.Entity;
import com.dc.csrpg.model.battle.Skill;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Grid<Tile> worldGrid = Loader.loadGridFromFile("big-world.map");

    TownModel model = new TownModel(worldGrid);
    TownPanel townPanel = new TownPanel(new TownController(model), model);

    Entity player = new Entity("Turing", 10, 100, 100, getPlayerSkills());
    Entity enemy = new Entity("Stallman", 10, 100, 100, getEnemySkills());

    Battle battle = new Battle(player, enemy, true);
    BattleController battleController = new BattleController(battle);
    BattlePanel battlePanel = new BattlePanel(battleController);
    battleController.setView(battlePanel);

    frame.add(new GamePanel(townPanel, battlePanel));
    frame.pack();
    frame.setVisible(true);
  }

  private static List<Skill> getPlayerSkills() {
    ArrayList<Skill> skills = new ArrayList<Skill>();
    skills.add(new Skill("Bow Hunting Skills", 10));
    skills.add(new Skill("Computer Hacking Skills", 20));
    skills.add(new Skill("Moustache Growing", 30));
    skills.add(new Skill("Nun-chuck Skills", 30));

    return skills;
  }

  private static List<Skill> getEnemySkills() {
    ArrayList<Skill> skills = new ArrayList<Skill>();
    skills.add(new Skill("Personal Hygiene Deprecation", 10));
    skills.add(new Skill("HURD Speed", 20));
    skills.add(new Skill("Horribly Illogical Opinions", 30));
    skills.add(new Skill("Fear of Corporations", 30));

    return skills;
  }
}

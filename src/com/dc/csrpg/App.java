package com.dc.csrpg;

import javax.swing.JFrame;

public class App {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Grid<Tile> worldGrid = Loader.loadGridFromFile("big-world.map");

    TownModel model = new TownModel(worldGrid);
    TownPanel townPanel = new TownPanel(new TownController(model), model);

    BattlePanel battlePanel = new BattlePanel();
    frame.add(new GamePanel(townPanel, battlePanel));
    frame.pack();
    frame.setVisible(true);
  }
}

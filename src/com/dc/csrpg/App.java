package com.dc.csrpg;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    TownModel model = new TownModel();
    TownPanel townPanel = new TownPanel(new TownController(model), model);

    JPanel panel = new JPanel();
    panel.add(townPanel, BorderLayout.CENTER);
    panel.add(new BattlePanel(), BorderLayout.LINE_END);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}

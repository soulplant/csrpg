package com.dc.csrpg;

import javax.swing.JFrame;

public class App {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    TownModel model = new TownModel();
    TownPanel townPanel = new TownPanel(new TownController(model), model);

    frame.add(townPanel);
    frame.pack();
    frame.setVisible(true);
  }
}

package com.dc.csrpg;

import javax.swing.JFrame;

public class App {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(new BattlePanel());
    frame.pack();
    frame.setVisible(true);
  }
}

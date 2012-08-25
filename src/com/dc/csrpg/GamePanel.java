package com.dc.csrpg;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
  public GamePanel(final TownPanel townPanel, final BattlePanel battlePanel) {
    add(townPanel, BorderLayout.CENTER);
    add(battlePanel, BorderLayout.LINE_END);
    townPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        townPanel.requestFocus();
      }
    });
    battlePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        battlePanel.requestFocus();
      }
    });
  }
}

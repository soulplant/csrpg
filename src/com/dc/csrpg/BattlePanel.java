package com.dc.csrpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel {
  private Color color = Color.ORANGE;

  public BattlePanel() {
    setPreferredSize(new Dimension(640, 480));
    setFocusable(true);
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        color = Color.CYAN;
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(color);
    g.fillRect(0, 0, getWidth(), getHeight());
  }
}

package com.dc.csrpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TownPanel extends JPanel {
  public TownPanel() {
    setPreferredSize(new Dimension(640, 480));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.GREEN);
    g.fillRect(0, 0, getWidth(), getHeight());
  }
}

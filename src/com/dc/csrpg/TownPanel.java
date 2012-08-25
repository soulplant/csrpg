package com.dc.csrpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TownPanel extends JPanel {
  private static final Color PLAYER_COLOR = Color.GREEN;

  private final int PLAYER_WIDTH_PX = 32;
  private final int PLAYER_HEIGHT_PX = 32;

  private final TownController controller;
  private final TownModel model;

  public TownPanel(final TownController controller, TownModel model) {
    this.controller = controller;
    this.model = model;
    setFocusable(true);
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          controller.onPlayerMove(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          controller.onPlayerMove(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          controller.onPlayerMove(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          controller.onPlayerMove(Direction.RIGHT);
        }
        repaint();
      }
    });
    setPreferredSize(new Dimension(640, 480));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.GRAY);
    g.fillRect(0, 0, getWidth(), getHeight());
    drawPlayer(g, model.getPlayerPosition());
  }

  private void drawPlayer(Graphics g, Point point) {
    int x = point.x * PLAYER_WIDTH_PX;
    int y = point.y * PLAYER_HEIGHT_PX;
    g.setColor(PLAYER_COLOR);
    g.fillRect(x, y, PLAYER_WIDTH_PX, PLAYER_HEIGHT_PX);
  }
}

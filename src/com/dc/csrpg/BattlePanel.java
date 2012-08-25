package com.dc.csrpg;

import com.dc.csrpg.controller.battle.BattleController;
import com.dc.csrpg.view.battle.BattleView;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel implements BattleView {

  private static final int MENU_OFFSET_LEFT_PX = 10;

  private static final int HITBOX_OFFSET_LEFT_PX = 100;
  private static final int HITBOX_OFFSET_TOP_PX = 20;

  private static final int HITBOX_WIDTH_PX = 180;
  private static final int HITBOX_HEIGHT_PX = 30;


  private final BattleController controller;

  private int selectedIndex = 0;
  private final List<String> menuItems = new ArrayList<String>();

  private String playerName = "Player";
  private String enemyName = "Enemy";

  private int playerCurrentHp = 0;
  private int playerMaxHp = 100;
  private int enemyCurrentHp = 0;
  private int enemyMaxHp = 100;

  public BattlePanel(BattleController controller) {
    this.controller = controller;

    setPreferredSize(new Dimension(640, 480));
    setFocusable(true);

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:
          BattlePanel.this.controller.onMenuDown();
          break;
        case KeyEvent.VK_UP:
          BattlePanel.this.controller.onMenuUp();
          break;

        case KeyEvent.VK_ENTER:
        case KeyEvent.VK_SPACE:
          BattlePanel.this.controller.onMenuAction();
          break;

        case KeyEvent.VK_ESCAPE:
        case KeyEvent.VK_BACK_SPACE:

          break;

          default:
            break;
        }

        //BattlePanel.this.controller.onPlayerAttack();
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.ORANGE);
    g.fillRect(0, 0, getWidth(), getHeight());

    drawMenu(g);
    drawHitboxes(g);
  }

  private void drawMenu(Graphics g) {
    // bottom left hand corner menu for actions
    Point offset = new Point(0, 240);

    g.setColor(Color.BLACK);
    g.fillRect(offset.x, offset.y, 320, 240);

    drawMenuItems(g, offset, menuItems);
  }

  private void drawMenuItems(Graphics g, Point offset, List<String> items) {
    Font f = new Font("Courier", 0, 16);
    g.setFont(f);
    g.setColor(Color.WHITE);

    for (int i = 0; i < items.size(); i++) {
      String itemName = items.get(i);
      if (i == selectedIndex) {
        itemName = "> " + itemName;
      } else {
        itemName = "  " + itemName;
      }
      g.drawString(itemName, offset.x + MENU_OFFSET_LEFT_PX, offset.y + ((i+1) * 40));
    }
  }

  private void drawHitboxes(Graphics g) {
    // bottom right hand corner menu for hit boxes
    g.setColor(Color.BLACK);
    g.fillRect(320, 240, 320, 240);

    drawHitbox(g, new Point(320, 240), playerName, playerCurrentHp, playerMaxHp);
    drawHitbox(g, new Point(320, 360), enemyName, enemyCurrentHp, enemyMaxHp);
  }

  private void drawHitbox(Graphics g, Point offset, String name, int currentHp, int maxHp) {
    int healthWidthPx = (int) ((float) currentHp / (float) maxHp * HITBOX_WIDTH_PX);

    Font f = new Font("Arial", 0, 16);
    g.setFont(f);
    g.setColor(Color.WHITE);
    g.drawString(name + " HP", offset.x, offset.y + 40);

    g.setColor(Color.RED);
    g.fillRect(offset.x + HITBOX_OFFSET_LEFT_PX, offset.y + HITBOX_OFFSET_TOP_PX,
        HITBOX_WIDTH_PX, HITBOX_HEIGHT_PX);

    g.setColor(Color.GREEN);
    g.fillRect(offset.x + HITBOX_OFFSET_LEFT_PX, offset.y + HITBOX_OFFSET_TOP_PX,
        healthWidthPx, HITBOX_HEIGHT_PX);

    g.setColor(Color.WHITE);
    g.drawString("" + currentHp, offset.x + HITBOX_OFFSET_LEFT_PX + healthWidthPx,
        offset.y + 40 + HITBOX_HEIGHT_PX);

    g.setColor(Color.WHITE);
    g.drawString("" + maxHp, offset.x + HITBOX_OFFSET_LEFT_PX + HITBOX_WIDTH_PX,
        offset.y + 40 + HITBOX_HEIGHT_PX);
  }

  @Override
  public void showMenu(List<String> items) {
    menuItems.clear();
    menuItems.addAll(items);
    repaint();
  }

  @Override
  public void setSelectedIndex(int selectedIndex) {
    this.selectedIndex = selectedIndex;
    repaint();
  }

  @Override
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  @Override
  public void setEnemyName(String enemyName) {
    this.enemyName = enemyName;
  }

  @Override
  public void updatePlayerHp(int currentHp, int maxHp) {
    playerCurrentHp = currentHp;
    playerMaxHp = maxHp;
    repaint();
  }

  @Override
  public void updateEnemyHp(int currentHp, int maxHp) {
    enemyCurrentHp = currentHp;
    enemyMaxHp = maxHp;
    repaint();
  }

  @Override
  public void refresh() {
    repaint();
  }

}

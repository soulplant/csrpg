package com.dc.csrpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel {
  enum Menu {
    MAIN,
    SKILLS,
  }

  private static final List<String> MAIN_ITEMS = new ArrayList<String>();
  static {
    MAIN_ITEMS.add("Attack");
    MAIN_ITEMS.add("Skills");
  };

  private Color color = Color.ORANGE;
  private Menu currentMenu = Menu.MAIN;
  private int selectedIndex = 0;

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

    drawMenu(g);
    drawHitboxes(g);

  }

  private void drawMenu(Graphics g) {
    // bottom left hand corner menu for actions
    Point offset = new Point(0, 240);
    
    g.setColor(Color.BLACK);
    g.fillRect(offset.x, offset.y, 320, 240);
    
    switch (currentMenu)
    {
    case MAIN:
      drawMenuItems(g, offset, MAIN_ITEMS);
      break;

    case SKILLS:
      drawMenuItems(g, offset, getSkillsList());
      break;

    default:
      break;

    }

  }
  
  private void drawMenuItems(Graphics g, Point offset, List<String> items) {
    Font f = new Font("Arial", 0, 32);
    g.setFont(f);
    g.setColor(Color.WHITE);
    
    for (int i = 0; i < items.size(); i++) {
      g.drawString(items.get(i), offset.x, offset.y + ((i+1) * 40));
    }
  }

  private List<String> getSkillsList() {
    ArrayList<String> skills = new ArrayList<String>();
    skills.add("Bow Skills");
    skills.add("Computer Hacking Skills");
    skills.add("Moustache Growing");
    
    return skills;
  }

  private void drawHitboxes(Graphics g) {
    // bottom right hand corner menu for hit boxes
    g.setColor(Color.BLACK);
    g.fillRect(320+2, 240, 320, 240);
  }  

}

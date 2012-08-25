package com.dc.csrpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import com.dc.csrpg.Tile.Type;

@SuppressWarnings("serial")
public class TownPanel extends JPanel {
  private static final Color PLAYER_COLOR = Color.RED;
  private static final Color GRASS_COLOR = Color.GREEN;
  private static final Color WALL_COLOR = Color.GRAY;
  private static final Color NPC_COLOR = Color.CYAN;
  private static final Color ITEM_COLOR = Color.YELLOW;
  private static final Color ALERT_BACKGROUND_COLOR = Color.BLACK;
  private static final Color ALERT_TEXT_COLOR = Color.WHITE;

  private final int PLAYER_WIDTH_PX = 32;
  private final int PLAYER_HEIGHT_PX = 32;

  private final TownModel model;

  public TownPanel(final TownController controller, TownModel model) {
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

  private static class RectDrawer {
    private final Graphics g;

    public RectDrawer(Graphics g) {
      this.g = g;
    }

    public void drawRect(int x, int y, Color color) {
      g.setColor(color);
      g.fillRect(x * Constants.TILE_WIDTH_PX, y * Constants.TILE_HEIGHT_PX,
          Constants.TILE_WIDTH_PX, Constants.TILE_HEIGHT_PX);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.GRAY);
    g.fillRect(0, 0, getWidth(), getHeight());
    drawTiles(g, model.getViewportTiles());
    drawPlayer(g, model.getPlayerViewportPosition());
    drawViewportPosition(g, model.getViewportPosition());
    drawAlert(g, model.getAlert());
  }

  private void drawAlert(Graphics g, String alert) {
    if (alert == null) {
      return;
    }
    setFont(new Font("Arial", 0, 24));
    FontMetrics fontMetrics = g.getFontMetrics();
    Rectangle2D stringBounds = fontMetrics.getStringBounds(alert, g);
    int stringWidth = (int) stringBounds.getWidth();
    int stringHeight = (int) stringBounds.getHeight();
    int x = (getWidth() - stringWidth) / 2;
    int y = (getHeight() - stringHeight) / 2;

    g.setColor(ALERT_BACKGROUND_COLOR);
    int paddingPx = 5;
    int padding = 2 * paddingPx;
    g.fillRect(x - padding, y - padding, stringWidth + padding,
        stringHeight + padding);
    g.setColor(ALERT_TEXT_COLOR);

    g.drawString(alert, x - paddingPx, y - paddingPx + stringHeight - fontMetrics.getDescent());
  }

  private void drawViewportPosition(Graphics g, Point viewportPosition) {
    Point vp = viewportPosition;
    g.setColor(Color.BLACK);
    g.drawString("(" + vp.x + ", " + vp.y + ")", 5, 15);
  }

  private void drawTiles(Graphics g, Grid<Tile>.Subview tiles) {
    RectDrawer d = new RectDrawer(g);
    for (int x = 0; x < tiles.getWidth(); x++) {
      for (int y = 0; y < tiles.getHeight(); y++) {
        Tile tile = tiles.get(x, y);
        d.drawRect(x, y, tileTypeToColor(tile.type));
        if (tile.getNPC() != null) {
          d.drawRect(x, y, NPC_COLOR);
        }
        if (tile.getItem() != null) {
          d.drawRect(x, y, ITEM_COLOR);
        }
      }
    }
  }

  private Color tileTypeToColor(Type type) {
    switch (type) {
    case GRASS: return GRASS_COLOR;
    case WALL: return WALL_COLOR;
    default: throw new IllegalArgumentException();
    }
  }

  private void drawPlayer(Graphics g, Point point) {
    int x = point.x * PLAYER_WIDTH_PX;
    int y = point.y * PLAYER_HEIGHT_PX;
    g.setColor(PLAYER_COLOR);
    g.fillRect(x, y, PLAYER_WIDTH_PX, PLAYER_HEIGHT_PX);
  }
}

package com.dc.csrpg;

public class Tile {
  public enum Type {
    GRASS, WALL
  }

  public final boolean passable;
  public final Type type;

  private Item item;
  private NPC npc;

  public Tile(Type type, boolean passable) {
    this.type = type;
    this.passable = passable;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public void setNPC(NPC npc) {
    this.npc = npc;
  }

  public Item getItem() {
    return item;
  }

  public NPC getNPC() {
    return npc;
  }

  public boolean hasNPC() {
    return npc != null;
  }

  public boolean hasItem() {
    return item != null;
  }

  public void clearItem() {
    item = null;
  }
}

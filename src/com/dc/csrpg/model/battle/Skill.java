package com.dc.csrpg.model.battle;

public class Skill {

  private final String name;
  private final int baseDamage;

  public Skill(String name, int baseDamage) {
    super();
    this.name = name;
    this.baseDamage = baseDamage;
  }

  public String getName() {
    return name;
  }

  public int getBaseDamage() {
    return baseDamage;
  }

}

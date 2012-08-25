package com.dc.csrpg.model.battle;

import java.util.List;

public class Entity {

  private String name;
  private int attackDamage;
  private int maxHp;
  private int currentHp;
  private List<Skill> skills;

  public Entity(String name, int attackDamage, int maxHp, int currentHp, List<Skill> skills) {
    this.name = name;
    this.attackDamage = attackDamage;
    this.maxHp = maxHp;
    this.currentHp = currentHp;
    this.skills = skills;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(List<Skill> skills) {
    this.skills = skills;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public void setCurrentHp(int currentHp) {
    this.currentHp = currentHp;
  }

  public void takeHit(int hp) {
    currentHp -= hp;
    currentHp = Math.max(0, currentHp);
  }

  public void heal(int hp) {
    currentHp += hp;
    currentHp = Math.min(maxHp, currentHp);
  }

  public boolean isAlive() {
    return currentHp > 0;
  }

  public int getAttackDamage() {
    return attackDamage;
  }

  public void setAttackDamage(int attackDamage) {
    this.attackDamage = attackDamage;
  }

}

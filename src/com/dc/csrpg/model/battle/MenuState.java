package com.dc.csrpg.model.battle;


public class MenuState {
  public enum Menu {
    MAIN,
    SKILLS,
  }

  public MenuState(Menu currentMenu, int selectedIndex) {
    this.currentMenu = currentMenu;
    this.selectedIndex = selectedIndex;
  }

  private Menu currentMenu;
  private int selectedIndex;

  public Menu getCurrentMenu() {
    return currentMenu;
  }

  public void setCurrentMenu(Menu currentMenu) {
    this.currentMenu = currentMenu;
  }

  public int getSelectedIndex() {
    return selectedIndex;
  }

  public void setSelectedIndex(int selectedIndex) {
    this.selectedIndex = selectedIndex;
  }

  public void maybeMoveUp(int menuLength) {
    maybeChangeIndex(selectedIndex - 1, menuLength);
  }

  public void maybeMoveDown(int menuLength) {
    maybeChangeIndex(selectedIndex + 1, menuLength);
  }

  private void maybeChangeIndex(int desiredIndex, int menuLength) {
    switch (currentMenu) {
    case MAIN:
      if ((desiredIndex >= 0) && (desiredIndex < menuLength)) {
        selectedIndex = desiredIndex;
      }
      break;
    case SKILLS:
      if ((desiredIndex >= 0) && (desiredIndex < menuLength)) {
        selectedIndex = desiredIndex;
      }
      break;
    default:
      throw new IllegalStateException();
    }
  }
}

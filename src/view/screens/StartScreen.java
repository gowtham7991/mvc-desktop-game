package view.screens;

import controller.Features;

public interface StartScreen {
  void setFeatures(Features f);
  void showScreen();
  void hideScreen();
  void setModel();
}

package view.screens;

import controller.Features;
import model.Model;

public interface SplashScreen {
  void setFeatures(Features f);
  void showScreen();
  void hideScreen();
  void setModel(Model m);
}

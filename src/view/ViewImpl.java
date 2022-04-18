package view;

import controller.Features;
import java.awt.*;
import java.util.List;
import model.Model;
import view.screens.GameScreen;
import view.screens.GameScreenImpl;
import view.screens.SplashScreen;
import view.screens.SplashScreenImpl;
import view.screens.StartScreen;
import view.screens.StartScreenImpl;

public class ViewImpl implements View{
  private final SplashScreen splashScreen;
  private final StartScreen startScreen;
  private final GameScreen gameScreen;

  public ViewImpl(Model m) {
    this.splashScreen = new SplashScreenImpl(m);
    this.startScreen = new StartScreenImpl(m);
    this.gameScreen = new GameScreenImpl(m);
  }

  @Override
  public void setFeatures(Features f) {

  }

  @Override
  public void setModel(Model m) {
    splashScreen.setModel(m);
    startScreen.setModel(m);
    gameScreen.setModel(m);
  }

  @Override
  public void startGame() {

  }

  @Override
  public void begin() {

  }

  @Override
  public void refresh() {

  }

  @Override
  public List<String> openAddPlayerPrompt() {
    return null;
  }

  @Override
  public String openPickUpItemPrompt(List<String> options) {
    return null;
  }

  @Override
  public String openAttackPrompt(List<String> options) {
    return null;
  }

  @Override
  public String openMovePetPrompt(List<String> options) {
    return null;
  }

  @Override
  public String openMovePrompt() {
    return null;
  }

  @Override
  public String openLookAroundPrompt(String text) {
    return null;
  }

  @Override
  public String showSuccessMessage(String title, String message) {
    return null;
  }

  @Override
  public String showErrorMessage(String title, String message) {
    return null;
  }
}

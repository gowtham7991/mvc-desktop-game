package view;

import controller.Features;
import java.awt.*;
import java.util.List;
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

  public ViewImpl() {
    this.splashScreen = new SplashScreenImpl();
    this.startScreen = new StartScreenImpl();
    this.gameScreen = new GameScreenImpl();
  }

  @Override
  public void setFeatures(Features f) {

  }

  @Override
  public void setModel() {

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
  public String openPickUpItemPrompt() {
    return null;
  }

  @Override
  public String openAttackPrompt() {
    return null;
  }

  @Override
  public String openMovePetPrompt() {
    return null;
  }

  @Override
  public String openMovePrompt() {
    return null;
  }

  @Override
  public String openLookAroundPrompt() {
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

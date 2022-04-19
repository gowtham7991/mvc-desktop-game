package view;

import controller.Features;
import java.util.List;
import javax.swing.*;
import model.ReadOnlyModel;
import view.screens.GameScreenImpl;
import view.screens.Screen;
import view.screens.SplashScreenImpl;
import view.screens.StartScreenImpl;

public class ViewImpl implements View{
  private final Screen splashScreen;
  private final Screen startScreen;
  private final Screen gameScreen;

  public ViewImpl(ReadOnlyModel m) {
    this.splashScreen = new SplashScreenImpl(m);
    this.startScreen = new StartScreenImpl(m);
    this.gameScreen = new GameScreenImpl(m);
  }

  @Override
  public void setFeatures(Features f) {
    splashScreen.setFeatures(f);
    startScreen.setFeatures(f);
    gameScreen.setFeatures(f);
  }

  @Override
  public void setModel(ReadOnlyModel m) {
    splashScreen.setModel(m);
    startScreen.setModel(m);
    gameScreen.setModel(m);
  }

  @Override
  public void startGame() {
    splashScreen.hideScreen();
    startScreen.showScreen();
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
  public String openPrompt(List<String> list, String prompt) {
    Object[] options = list.toArray();
    String response = (String) JOptionPane.showInputDialog(null, "Choose a room!", "Move Pet",
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    return response;
  }

  @Override
  public void openLookAroundPrompt(String text) {

  }

  @Override
  public void openGameOverPrompt(String winner) {

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

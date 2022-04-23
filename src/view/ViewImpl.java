package view;

import controller.Features;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.ReadOnlyModel;
import view.screens.GameScreenImpl;
import view.screens.Screen;
import view.screens.WelcomeScreenImpl;
import view.screens.SpawnScreenImpl;

public class ViewImpl implements View{
  private final Screen welcomeScreen;
  private final Screen spawnScreen;
  private final Screen gameScreen;

  public ViewImpl(ReadOnlyModel m) {
    this.welcomeScreen = new WelcomeScreenImpl(m);
    this.spawnScreen = new SpawnScreenImpl(m);
    this.gameScreen = new GameScreenImpl(m);
  }

  @Override
  public void setFeatures(Features f) {
    welcomeScreen.setFeatures(f);
    spawnScreen.setFeatures(f);
    gameScreen.setFeatures(f);
    welcomeScreen.showScreen();
    spawnScreen.resetFocus();
  }

  @Override
  public void setModel(ReadOnlyModel m) {
    welcomeScreen.setModel(m);
    spawnScreen.setModel(m);
    gameScreen.setModel(m);
  }

  @Override
  public void startGame() {
    welcomeScreen.hideScreen();
    spawnScreen.showScreen();
    spawnScreen.resetFocus();
  }

  @Override
  public void begin() {
    spawnScreen.hideScreen();
    gameScreen.showScreen();
    gameScreen.resetFocus();
  }

  @Override
  public void reset() {
    welcomeScreen.showScreen();
    spawnScreen.hideScreen();
    gameScreen.hideScreen();
  }

  @Override
  public void refresh() {
    welcomeScreen.refresh();
    spawnScreen.refresh();
    gameScreen.refresh();
  }

  @Override
  public List<String> openAddPlayerPrompt(List<String> list) {
    List<String> response = new ArrayList<>();
    JTextField playerName = new JTextField();

    String[] spaceList = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      spaceList[i] = list.get(i);
    }

    String[] itemCountList = {"1","2","3","4","5","6","7","8","9","10"};
    JComboBox<String> spaceSelection = new JComboBox<String>(spaceList);
    JComboBox<String> itemCountSelection = new JComboBox<String>(itemCountList);

    final JComponent[] inputs = new JComponent[] { new JLabel("First"), playerName,
        new JLabel("Select a Room"), spaceSelection, new JLabel("Select maximum Items"), itemCountSelection };
    int result = JOptionPane.showConfirmDialog(null, inputs, "Add Player",
        JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      response.add(playerName.getText());
      response.add((String) spaceSelection.getSelectedItem());
      response.add((String) itemCountSelection.getSelectedItem());
    }
    return response;
  }

  @Override
  public String openPrompt(List<String> list, String prompt) {
    Object[] options = list.toArray();
    String response = (String) JOptionPane.showInputDialog(null, prompt, "",
        JOptionPane.QUESTION_MESSAGE, null, options, null);
    return response;
  }

  @Override
  public void openLookAroundPrompt(String text) {
    String[] buttons = { "OK" };
    JOptionPane.showOptionDialog(null, text, "Space Details",
        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null,
        buttons, buttons[0]);
  }

  @Override
  public void openGameOverPrompt(String winner) {
    String[] buttons = { "RESTART" , "QUIT" };
    try {
      ImageIcon icon = new ImageIcon(ImageIO.read(new File("res/GameOver.png")));
      JOptionPane.showOptionDialog(null, winner, "GAME OVER",
          JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, buttons, null);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot Read File!");
    }
  }

  @Override
  public File openFileUploadPrompt() {
    JFileChooser fileChooser = new JFileChooser(".");
    File selectedFile = null;
    int status = fileChooser.showOpenDialog(null);
    if (status == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
    }

    return selectedFile;
  }

  @Override
  public void resetFocus() {
    welcomeScreen.resetFocus();
    spawnScreen.resetFocus();
    gameScreen.resetFocus();
  }

  @Override
  public void showSuccessMessage(String title, String message) {
    JOptionPane.showMessageDialog(null,title,message,JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showErrorMessage(String title, String message) {
    JOptionPane.showMessageDialog(null, title, message,
        JOptionPane.ERROR_MESSAGE);
  }
}

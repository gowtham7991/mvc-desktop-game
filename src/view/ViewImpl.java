package view;

import controller.Features;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.ReadOnlyModel;
import view.screens.GameScreenImpl;
import view.screens.Screen;
import view.screens.SpawnScreenImpl;
import view.screens.WelcomeScreenImpl;

public class ViewImpl implements View {
  private final Screen welcomeScreen;
  private final Screen spawnScreen;
  private final Screen gameScreen;

  /**
   * Constructs the facade view class.
   * The model validation takes place in the driver class.
   * @param m the readonly model
   */
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
  public void quit() {
    welcomeScreen.quit();
    spawnScreen.quit();
    gameScreen.quit();
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

  /**
   * Validation of the list of spaces takes place in the controller and hence is not handled in the
   * view. If the list sent to the view is null, an error message is prompted.
   * @param l the list of spaces.
   * @return the response
   */
  @Override
  public List<String> openAddPlayerPrompt(List<String> l) {
    List<String> list;
    if (l == null) {
      list = new ArrayList<>();
    } else {
      list = l;
    }
    List<String> response = new ArrayList<>();
    JTextField playerName = new JTextField();

    String[] spaceList = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      spaceList[i] = list.get(i);
    }

    String[] itemCountList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    JComboBox<String> spaceSelection = new JComboBox<String>(spaceList);
    JComboBox<String> itemCountSelection = new JComboBox<String>(itemCountList);

    final JComponent[] inputs = new JComponent[] { new JLabel("First"), playerName,
        new JLabel("Select a Room"), spaceSelection,
        new JLabel("Select maximum Items"), itemCountSelection };
    int result = JOptionPane.showConfirmDialog(null, inputs, "Add Player",
        JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      response.add(playerName.getText());
      response.add((String) spaceSelection.getSelectedItem());
      response.add((String) itemCountSelection.getSelectedItem());
    }
    return response;
  }

  /**
   * Validation of the list of options takes place in the controller and hence is not handled in the
   * view. If the list sent to the view is null, an error message is prompted.
   * @param l the list of spaces.
   * @return the response
   */
  @Override
  public String openPrompt(List<String> l, String prompt) {
    List<String> list;
    if (l == null) {
      list = new ArrayList<>();
    } else {
      list = l;
    }
    Object[] options = list.toArray();
    String response = (String) JOptionPane.showInputDialog(null, prompt, "",
        JOptionPane.QUESTION_MESSAGE, null, options, null);
    return response;
  }

  @Override
  public void openLookAroundPrompt(String t) {
    String text;
    if (t == null) {
      text = "";
    } else {
      text = t;
    }
    String[] buttons = { "OK" };
    JOptionPane.showOptionDialog(null, text, "Space Details",
        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null,
        buttons, buttons[0]);
  }

  @Override
  public int openGameOverPrompt(String t) {
    String winner;
    if (t == null) {
      winner = "";
    } else {
      winner = t;
    }
    String[] buttons = { "RESTART",  "QUIT" };
    ImageIcon icon = new ImageIcon(getClass().getClassLoader()
        .getResource("assets/GameOver_200x200.png"));
    return JOptionPane.showOptionDialog(null, winner, "GAME OVER",
        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, icon, buttons, null);

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
  public void showSuccessMessage(String t, String m) {
    String title;
    String message;
    if (t == null || m == null) {
      title = "";
      message = "";
    } else {
      title = t;
      message = m;
    }
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showErrorMessage(String t, String m) {
    String title;
    String message;
    if (t == null || m == null) {
      title = "";
      message = "";
    } else {
      title = t;
      message = m;
    }
    JOptionPane.showMessageDialog(null, message, title,
        JOptionPane.ERROR_MESSAGE);
  }
}

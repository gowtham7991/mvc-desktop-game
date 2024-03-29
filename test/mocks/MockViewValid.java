package mocks;

import controller.Features;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import view.View;

/**
 * This is a mock view class. Its function is to test the controller.
 *
 */
public class MockViewValid implements View {
  private StringBuffer log;
  private int uniqueNumber;

  /**
   * This is the constructor of the class.
   * @param log the log for each method.
   * @param uniqueNumber the uniwue number.
   */
  public MockViewValid(StringBuffer log, int uniqueNumber) {
    this.log = log;
    this.uniqueNumber = uniqueNumber;
  }

  @Override
  public void setFeatures(Features f) {
    log.append("Inside method setFeatures. Id = ").append(uniqueNumber);

  }

  @Override
  public void startGame() {
    log.append("Inside method startGame. Id = ").append(uniqueNumber);

  }

  @Override
  public void begin() {
    log.append("Inside method begin. Id = ").append(uniqueNumber);

  }

  @Override
  public void quit() {
    log.append("Inside method quit. Id = ").append(uniqueNumber);
  }

  @Override
  public void reset() {
    log.append("Inside method reset. Id = ").append(uniqueNumber);

  }

  @Override
  public void refresh() {
    log.append("Inside method refresh. Id = ").append(uniqueNumber);

  }

  @Override
  public List<String> openAddPlayerPrompt(List<String> spaces) {
    List<String> list = new ArrayList<String>();
    list.add("player");
    list.add("space");
    list.add("1");
    log.append("Inside method openAddPlayerPrompt. Id = ").append(uniqueNumber).append(spaces);
    return list;
  }

  @Override
  public String openPrompt(List<String> options, String prompt) {
    log.append("Inside method openPrompt. Id = ").append(uniqueNumber).append(prompt)
        .append(options);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public void openLookAroundPrompt(String text) {
    log.append("Inside method openLookAroundPrompt. Id = ").append(uniqueNumber).append(text);

  }

  @Override
  public int openGameOverPrompt(String winner) {
    log.append("Inside method openGameOverPrompt. Id = ").append(uniqueNumber).append(winner);
    return uniqueNumber;
  }

  @Override
  public File openFileUploadPrompt() {
    File file = new File("res\\assets\\ConfigFile.txt");
    log.append("Inside method openFileUploadPrompt. Id = ").append(uniqueNumber);
    return file;
  }

  @Override
  public void resetFocus() {
    log.append("Inside method resetFocus. Id = ").append(uniqueNumber);

  }

  @Override
  public void showSuccessMessage(String title, String message) {
    log.append("Inside method showSuccessMessage. Id = ").append(uniqueNumber).append(title)
        .append(message);

  }

  @Override
  public void showErrorMessage(String title, String message) {
    log.append("Inside method showErrorMessage. Id = ").append(uniqueNumber).append(title)
        .append(message);

  }
}

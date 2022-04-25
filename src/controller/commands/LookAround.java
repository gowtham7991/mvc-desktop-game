package controller.commands;

import model.Model;
import view.View;

/**
 * This is the command class for the command to look around.
 *
 */
public class LookAround implements Command {

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    try {
      String response = m.lookAround();
      v.openLookAroundPrompt(response);
      v.refresh();
    } catch (IllegalArgumentException e) {
      v.showErrorMessage("Could not retrieve information!", e.getMessage());
    }
  }
}

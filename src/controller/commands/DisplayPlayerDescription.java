package controller.commands;

import model.Model;
import view.View;

/**
 * This is the command class for the command to display player information.
 *
 */
public class DisplayPlayerDescription implements Command {

  @Override
  public void execute(Model m, View v) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Invalid model or view!");
    }
    try {
      String response = m.displayPlayerDescription();
      v.showSuccessMessage("Player details", response);
      v.refresh();
    } catch (IllegalArgumentException e) {
      v.showErrorMessage("Failed to fetch player details", e.getMessage());
    }
  }
}

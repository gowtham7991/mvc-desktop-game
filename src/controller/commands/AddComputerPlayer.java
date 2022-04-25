package controller.commands;

import model.Model;
import view.View;

/**
 * This is the command class for the command to a add computer player.
 *
 */
public class AddComputerPlayer implements Command {
  
  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    try {
      String result = m.addComputerPlayer();
      v.showSuccessMessage("Player added!", result);
    } catch (IllegalArgumentException e) {
      v.showErrorMessage("Failed to add the player", e.getMessage());
    }
    v.refresh();
  }
}

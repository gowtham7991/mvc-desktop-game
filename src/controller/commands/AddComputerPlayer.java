package controller.commands;

import model.Model;
import view.View;

public class AddComputerPlayer implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    try {
      String result = m.addComputerPlayer();
      v.showSuccessMessage(result, "Player added!");
    } catch (IllegalArgumentException e) {
      v.showErrorMessage(e.getMessage(), "Failed to add player");
    }
    v.refresh();
  }
}

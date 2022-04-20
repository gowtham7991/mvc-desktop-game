package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class MovePet implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    List<String> spaces = m.getAllSpaces();
    String response = v.openPrompt(spaces, "Choose a space");
    if (!"cancel".equalsIgnoreCase(response)) {
      try {
        m.movePet(response);
        v.showSuccessMessage("Pet moved!", "");
        if (m.isGameOver()) {
          String winner = m.getWinner();
          v.openGameOverPrompt(winner);
        } else {
          v.refresh();
        }
      } catch (IllegalArgumentException e) {
        v.showErrorMessage("Failed to move",e.getMessage());
      }
    }

  }
}

package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class MovePet implements Command{
  private final View view;

  public MovePet(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    List<String> spaces = m.getAllSpaces();
    String response = view.openPrompt(spaces, "Choose a space");
    if (!"cancel".equalsIgnoreCase(response)) {
      try {
        m.movePet(response);
        view.showSuccessMessage("Pet moved!", "");
        if (m.isGameOver()) {
          String winner = m.getWinner();
          view.openGameOverPrompt(winner);
        } else {
          view.refresh();
        }
      } catch (IllegalArgumentException e) {
        view.showErrorMessage("Failed to move",e.getMessage());
      }
    }

  }
}

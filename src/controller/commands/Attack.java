package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class Attack implements Command{
  private final View view;

  public Attack(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    List<String> itemList = m.getItemsInCurrentSpace();
    String response = view.openPrompt(itemList, "Choose an item");
    if (!"cancel".equalsIgnoreCase(response)) {
      try {
        m.attack(response);
        view.showSuccessMessage("Player added!", "");
        if (m.isGameOver()) {
          String winner = m.getWinner();
          view.openGameOverPrompt(winner);
        } else {
          view.refresh();
        }
      } catch (IllegalArgumentException e) {
        view.showErrorMessage("Failed to add the player",e.getMessage());
      }
    }
  }
}

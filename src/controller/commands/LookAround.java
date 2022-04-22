package controller.commands;

import model.Model;
import view.View;

public class LookAround implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    try {
      String response = m.lookAround();
      v.openLookAroundPrompt(response);
      v.refresh();
      while (m.isComputerInTurn()) {
        v.showSuccessMessage("", "Computer player took a turn!");
        v.refresh();
      }
      if (m.isGameOver()) {
        String winner = m.getWinner();
        v.openGameOverPrompt(winner);
      }
    } catch (IllegalArgumentException e) {
      v.showErrorMessage("Could not retrieve information!",e.getMessage());
    }
  }
}

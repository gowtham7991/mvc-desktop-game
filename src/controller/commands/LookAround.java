package controller.commands;

import model.Model;
import view.View;

public class LookAround implements Command{
  private final View view;

  public LookAround(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    try {
      String response = m.lookAround();
      view.openLookAroundPrompt(response);
      if (m.isGameOver()) {
        String winner = m.getWinner();
        view.openGameOverPrompt(winner);
      } else {
        view.refresh();
      }
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Could not retrieve information!",e.getMessage());
    }
  }
}

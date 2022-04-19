package controller.commands;

import model.Model;
import view.View;

public class Move implements Command{
  private final View view;
  private final int x;
  private final int y;

  public Move(View view, int xCoodrinate, int yCoordinate) {
    this.view = view;
    this.x = xCoodrinate;
    this.y = yCoordinate;
  }

  @Override
  public void execute(Model m) {
    String response = view.openMovePrompt();
    try {
      m.move(response);
      view.showSuccessMessage("Player moved!", "");
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

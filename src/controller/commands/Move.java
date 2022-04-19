package controller.commands;

import model.Model;
import view.View;

public class Move implements Command{
  private final View view;
  private final int x;
  private final int y;

  public Move(View view, int xCoordinate, int yCoordinate) {
    this.view = view;
    this.x = xCoordinate;
    this.y = yCoordinate;
  }

  @Override
  public void execute(Model m) {
    try {
      m.move(x, y);
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

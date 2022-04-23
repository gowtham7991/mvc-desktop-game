package controller.commands;

import model.Model;
import view.View;

public class Move implements Command{
  private final int x;
  private final int y;

  public Move(int xCoordinate, int yCoordinate) {
    this.x = xCoordinate;
    this.y = yCoordinate;
  }

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    try {
      m.move(x, y);
      v.showSuccessMessage("Player moved!", "");
      v.refresh();
    } catch (IllegalArgumentException e) {
      v.showErrorMessage("Failed to move",e.getMessage());
    }
  }
}

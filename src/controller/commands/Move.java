package controller.commands;

import model.Model;
import view.View;

/**
 * This is the command class for the command to a add computer player.
 *
 */
public class Move implements Command {
  private final int x;
  private final int y;

  /**
   * Constructor for the move command.
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Move(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void execute(Model m, View v) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    try {
      m.move(x, y);
      v.showSuccessMessage("Player moved!", "");
      v.refresh();
    } catch (IllegalArgumentException e) {
      v.showErrorMessage("Failed to move", e.getMessage());
    }
  }
}

package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class AddPlayer implements Command{
  private final View view;

  public AddPlayer(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    try {
      m.addComputerPlayer();
      view.showSuccessMessage("Player added!", "");
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Failed to add player",e.getMessage());
    }
  }
}

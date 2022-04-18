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
    List<String> response = view.openAddPlayerPrompt();
    if (response.size() != 0) {
      try {
        m.addPlayer(response.get(0), response.get(1), Integer.parseInt(response.get(2)));
        view.showSuccessMessage("Player added!", "");
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showErrorMessage("Failed to add the player",e.getMessage());
      }
    }
  }
}

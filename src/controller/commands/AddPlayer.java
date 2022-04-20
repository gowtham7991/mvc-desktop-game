package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class AddPlayer implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    List<String> spaces = m.getAllSpaces();
    List<String> response = v.openAddPlayerPrompt(spaces);

    if (response.size() != 0) {
      try {
        String result = m.addPlayer(response.get(0), response.get(1), Integer.parseInt(response.get(2)));
        v.showSuccessMessage(result, "Player added!");
        v.refresh();
      } catch (IllegalArgumentException e) {
        v.showErrorMessage(e.getMessage(), "Failed to add the player");
      }
    }
  }
}

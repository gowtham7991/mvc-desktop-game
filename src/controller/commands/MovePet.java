package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class MovePet implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    List<String> spaces = m.getAllSpaces();

    if (spaces == null) {
      v.showErrorMessage("Failed", "Could not retrieve space list");
    }

    String response = v.openPrompt(spaces, "Choose a space");
    if (response != null) {
      try {
        String result = m.movePet(response);
        v.showSuccessMessage("Pet moved!", result);
        v.refresh();
      } catch (IllegalArgumentException e) {
        v.showErrorMessage("Failed to move", e.getMessage());
      }
    }

  }
}

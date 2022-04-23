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
    String response = v.openPrompt(spaces, "Choose a space");
    if (response != null) {
      try {
        m.movePet(response);
        v.showSuccessMessage("Pet moved!", "");
        v.refresh();
      } catch (IllegalArgumentException e) {
        v.showErrorMessage("Failed to move",e.getMessage());
      }
    }

  }
}

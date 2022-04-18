package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class MovePet implements Command{
  private final View view;

  public MovePet(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    List<String> spaces = m.getAllSpaces();
    String response = view.openMovePetPrompt(spaces);

    try {
      m.movePet(response);
      view.showSuccessMessage("Pet moved!", "");
      view.refresh();
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Failed to move",e.getMessage());
    }
  }
}

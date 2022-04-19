package controller.commands;

import model.Model;
import view.View;

public class DisplayPlayerDescription implements Command{
  private final View view;

  public DisplayPlayerDescription(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    try {
      String response = m.displayPlayerDescription();
      view.showSuccessMessage("Player details", response);
      view.refresh();
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Failed to fetch player details",e.getMessage());
    }
  }
}

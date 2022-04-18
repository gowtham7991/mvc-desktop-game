package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class PickUpItem implements Command{
  private final View view;

  public PickUpItem(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {
    List<String> itemsInSpace = m.getItemsInCurrentSpace();
    String response = view.openPickUpItemPrompt(itemsInSpace);

    try {
      m.pickUpItem(response);
      view.showSuccessMessage("Item picked up!", "");
      view.refresh();
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Failed to pick up item",e.getMessage());
    }
  }
}

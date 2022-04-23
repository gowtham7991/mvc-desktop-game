package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class PickUpItem implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }

    List<String> itemsInSpace = m.getItemsInCurrentSpace();
    String response = v.openPrompt(itemsInSpace, "Pick an item");
    if (response != null) {
      try {
        m.pickUpItem(response);
        v.showSuccessMessage("Item picked up!", "");
        v.refresh();
      } catch (IllegalArgumentException e) {
        v.showErrorMessage("Failed to pick up item",e.getMessage());
      }
    }
  }
}

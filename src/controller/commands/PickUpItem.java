package controller.commands;

import java.util.List;
import model.Model;
import view.View;

/**
 * This is the command class for the command to a pick up an item.
 *
 */
public class PickUpItem implements Command {

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }

    List<String> itemsInSpace = m.getItemsInCurrentSpace();

    if (itemsInSpace == null) {
      v.showErrorMessage("Failed!", "Failed to retrieve item list.");
    } else {
      String response = v.openPrompt(itemsInSpace, "Pick an item");
      if (response != null) {
        try {
          String result = m.pickUpItem(response);
          v.showSuccessMessage("Item picked up!", result);
          v.refresh();
        } catch (IllegalArgumentException e) {
          v.showErrorMessage("Failed to pick up item", e.getMessage());
        }
      }
    }
  }
}

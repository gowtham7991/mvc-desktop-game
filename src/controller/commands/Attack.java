package controller.commands;

import java.util.List;
import model.Model;
import view.View;

public class Attack implements Command{

  @Override
  public void execute(Model m, View v) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model!");
    }
    List<String> itemList = m.getItemsOfPlayerInTurn();
    if (itemList == null) {
      v.showErrorMessage("Failed", "Could not retrieve item list");
    }
    itemList.add("poke");

    String response = v.openPrompt(itemList, "Choose an item");
    String result;
    if (response != null) {
      try {
        if ("poke".equalsIgnoreCase(response)) {
          result = m.attack();
        } else {
          result = m.attack(response);
        }
        v.showSuccessMessage("Target attacked!", result);
        v.refresh();
      } catch (IllegalArgumentException e) {
        v.showErrorMessage("Attack failed!", e.getMessage());
      }
    }
  }
}

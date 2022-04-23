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
        v.showSuccessMessage(result, "Target attacked!");
        v.refresh();
      } catch (IllegalArgumentException e) {
        v.showErrorMessage(e.getMessage(),"Attack failed!");
      }
    }
  }
}

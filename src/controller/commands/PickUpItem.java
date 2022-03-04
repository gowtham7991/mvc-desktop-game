package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import model.Model;

public class PickUpItem implements Command {
  private final Scanner scan;
  private final Appendable out;


  public PickUpItem (Scanner scan, Appendable out) {
    if ( scan == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void execute(Model m) {

    try {
      String itemName = null;
      String cmdResponse;
      boolean validExec = false;

      while (!validExec) {
        try {
          out.append("Available items : ");
          out.append(m.getItemsInCurrentSpace()).append("\n");
          out.append("Enter the item name: \n");
          itemName = scan.nextLine();
          cmdResponse = m.pickUpItem(itemName);
          out.append(cmdResponse);
          validExec = true;
        }
        catch (IllegalArgumentException e) {
          out.append("Could not pick up item.\n");
        }
      }
    }

    catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}

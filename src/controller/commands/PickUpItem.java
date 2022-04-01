package controller.commands;

import controller.Command;
import java.io.IOException;
import java.util.Scanner;
import model.Model;

/**
 * Command to pick up an item from the current place.
 */
public class PickUpItem implements Command {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to pick up item from the space.
   *
   * @param scan the scanner to read input
   * @param out  the output buffer to write the data
   */
  public PickUpItem(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void execute(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    try {
      String itemName = null;
      String cmdResponse;
      boolean validExec = false;

      while (!validExec) {
        try {
          out.append("Available items : ");
          out.append(m.getItemsInCurrentSpace()).append("\n");
          out.append("Enter the item name: \n");
          itemName = scan.nextLine().trim();
          cmdResponse = m.pickUpItem(itemName);
          out.append(cmdResponse).append("\n");
          validExec = true;
        } catch (IllegalArgumentException e) {
          out.append("Could not pick up item.\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}

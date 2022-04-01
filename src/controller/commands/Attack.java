package controller.commands;

import controller.Command;
import java.io.IOException;
import java.util.Scanner;
import model.Model;

/**
 * Command to attack the target with an item or poke him in the eye.
 */
public class Attack implements Command {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to attack the target.
   *
   * @param scan the scanner to read input
   * @param out  the output buffer to write the data
   */
  public Attack(Scanner scan, Appendable out) {
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
          out.append("Available items : ").append("\n");
          out.append(m.getItemsOfPlayerInTurn()).append("\n");
          out.append("Enter the item you would want to use or use poke: \n");
          itemName = scan.nextLine().trim();
          if ("poke".equals(itemName)) {
            cmdResponse = m.attack();
          } else {
            cmdResponse = m.attack(itemName);
          }
          out.append(cmdResponse).append("\n");
          validExec = true;
        } catch (IllegalArgumentException e) {
          out.append("Could not move! Retry.\n");
        }
      }

    } catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}

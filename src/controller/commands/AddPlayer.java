package controller.commands;

import controller.Command;
import java.io.IOException;
import java.util.Scanner;
import model.Model;

/**
 * Command to add a normal player to the game.
 */
public class AddPlayer implements Command {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to add a normal player.
   * @param scan the scanner to read input
   * @param out the output buffer to write the data
   */
  public AddPlayer(Scanner scan, Appendable out) {
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
      String name;
      String spaceName;
      String itemLimitStr = null;
      Integer itemLimit = null;
      String cmdResponse;
      boolean validExec = false;
      while (!validExec) {
        try {
          out.append("Enter your name: \n");
          name = scan.nextLine().trim();
          out.append("# List of available spaces #\n");
          out.append(m.getAllSpaces());
          out.append("Enter the Space you wish to enter: \n");
          spaceName = scan.nextLine().trim();

          while (itemLimit == null) {
            out.append("Enter the item limit: \n");
            itemLimitStr = scan.nextLine().trim();
            try {
              itemLimit = Integer.parseInt(itemLimitStr);
            } catch (NumberFormatException e) {
              out.append("Invalid item limit!\n");
              itemLimit = null;
            }
          }

          cmdResponse = m.addPlayer(name, spaceName, itemLimit);
          out.append(cmdResponse).append("\n");
          validExec = true;
        } catch (IllegalArgumentException e) {
          out.append("Could not add a player! Retry.\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}

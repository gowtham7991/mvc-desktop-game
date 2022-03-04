package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import model.Model;

public class AddPlayer implements Command {
  private final Scanner scan;
  private final Appendable out;


  public AddPlayer(Scanner scan, Appendable out) {
    if ( scan == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void execute(Model m) {

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
          name = scan.nextLine();
          out.append("Enter the Space you wish to enter: \n");
          spaceName = scan.nextLine();

          while (itemLimit == null) {
            out.append("Enter the item limit: \n");
            itemLimitStr = scan.nextLine();
            try {
              itemLimit = Integer.parseInt(itemLimitStr);
            }
            catch (NumberFormatException e) {
              out.append("Invalid item limit!\n");
              itemLimit = null;
            }
          }

          cmdResponse = m.addPlayer(name, spaceName, itemLimit);
          out.append(cmdResponse).append("\n");
          validExec = true;
        }
        catch (IllegalArgumentException e) {
          out.append("Could not add a player! Retry.\n");
        }
      }
    }
    catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}

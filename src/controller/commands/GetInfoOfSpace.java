package controller.commands;

import controller.Command;
import java.io.IOException;
import java.util.Scanner;
import model.Model;

/**
 * Command to get the information of a space in the world.
 */
public class GetInfoOfSpace implements Command {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to add a normal player.
   * @param scan the scanner to read input
   * @param out the output buffer to write the data
   */
  public GetInfoOfSpace(Scanner scan, Appendable out) {
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
      String spaceName = null;
      String cmdResponse;
      boolean validExec = false;
      while (!validExec) {
        try {
          out.append("Enter the name of the space: \n");
          spaceName = scan.nextLine().trim();
          cmdResponse = m.getInfoOfSpace(spaceName);
          out.append(cmdResponse);
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

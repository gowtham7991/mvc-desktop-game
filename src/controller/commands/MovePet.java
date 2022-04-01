package controller.commands;

import controller.Command;
import java.io.IOException;
import java.util.Scanner;
import model.Model;

/**
 * Command to move a player from the current space to required space.
 */
public class MovePet implements Command {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to add a normal player.
   *
   * @param scan the scanner to read input
   * @param out  the output buffer to write the data
   */
  public MovePet(Scanner scan, Appendable out) {
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
          out.append("Available spaces : ").append("\n");
          out.append(m.getAllSpaces()).append("\n");
          out.append("Enter the Space you wish to move the pet : \n");
          spaceName = scan.nextLine().trim();
          cmdResponse = m.movePet(spaceName);
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

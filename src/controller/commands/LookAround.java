package controller.commands;

import controller.Command;
import java.io.IOException;
import java.util.Scanner;
import model.Model;

/**
 * Command to look around the current space the player currently is in.
 */
public class LookAround implements Command {

  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to add a normal player.
   * @param scan the scanner to read input
   * @param out the output buffer to write the data
   */
  public LookAround(Scanner scan, Appendable out) {
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
      String cmdResponse;
      boolean validExec = false;
      while (!validExec) {
        try {
          cmdResponse = m.lookAround();
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

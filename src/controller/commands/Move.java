package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.GameController;
import model.game.Model;

public class Move implements GameController.Command {
  private final Scanner scan;
  private final Appendable out;

  public Move(Scanner scan, Appendable out) {
    if ( scan == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void execute(Model m) {

    try {
      String spaceName = null;
      String cmdResponse;
      boolean validExec = false;
      while (!validExec) {
        try {
          out.append(m.getNeighboursOfPlayerCurrentSpace());
          out.append("Enter the Space you wish to enter: \n");
          spaceName = scan.nextLine();
          cmdResponse = m.move(spaceName);
          out.append(cmdResponse);
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

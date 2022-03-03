package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.GameController;
import model.game.Model;

public class GetInfoOfSpace implements GameController.Command {
  private final Scanner scan;
  private final Appendable out;


  public GetInfoOfSpace(Scanner scan, Appendable out) {
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
          out.append("Enter the name of the space: \n");
          spaceName = scan.nextLine();
          cmdResponse = m.getInfoOfSpace(spaceName);
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
